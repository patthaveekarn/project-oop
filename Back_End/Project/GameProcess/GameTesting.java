package Back_End.Project.GameProcess;

import Back_End.Project.Player.Player;
import Back_End.Project.Region.ConfigPosition;
import Back_End.Project.Region.Position;
import Back_End.Project.Region.Region;
import Back_End.Project.Statement.DirectionNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public final class GameTesting {
    private ConfigGame game;
    private List<SampleRegion> territory;
    private SamplePlayer player1,player2;

    public static abstract class SampleRegion implements Region {
        public long deposit = 0;
        public Player owner = null;
    }

    public abstract static class SamplePlayer implements Player{
        public final Map<String, Long> identifiers = new HashMap<>();
        public SampleRegion cityCenter;
        public long budget = 1;

        public SamplePlayer(SampleRegion cityCenter){
            cityCenter.updateOwner(this);
            this.cityCenter = cityCenter;
        }
    }

    private static SampleRegion mockRegion(Position loc, long maxDeposit){
        return new SampleRegion() {
            private boolean isCityCenter;

            @Override
            public Player getOwner() {
                return owner;
            }

            @Override
            public boolean isCityCenter() {
                return isCityCenter;
            }

            @Override
            public void changeCityCenter() {
                isCityCenter = false;
            }

            @Override
            public Position getLocation() {
                return loc;
            }

            @Override
            public long getDeposit() {
                return deposit;
            }

            @Override
            public void updateOwner(Player owner) {
                this.owner = owner;
            }

            @Override
            public void updateDeposit(long amount) {
                deposit = Math.max(0, deposit + amount);
                deposit = Math.min(maxDeposit, deposit);
            }

            @Override
            public void setCityCenter(Player owner) {
                isCityCenter = true;
                updateOwner(owner);
            }
        };
    }

    private static List<SampleRegion> mockTerritory(int rows, int cols, long maxDeposit) {
        List<SampleRegion> regions = new ArrayList<>(rows * cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Position loc = new ConfigPosition(j, i);
                regions.add(mockRegion(loc, maxDeposit));
            }
        }
        return regions;
    }

    private static SamplePlayer mockPlayer(SampleRegion defineCenterLoc){
        SamplePlayer player = new SamplePlayer(defineCenterLoc) {
            @Override
            public long getID() {
                throw new GameException.NotImplemented();
            }

            @Override
            public String getName() {
                throw new GameException.NotImplemented();
            }

            @Override
            public long getBudget() {
                return budget;
            }

            @Override
            public boolean updateBudget(long amount) {
                boolean totalBudget = budget + amount >= 0;
                budget = Math.max(0, budget + amount);
                return totalBudget;
            }

            @Override
            public Map<String, Long> identifiers() {
                return identifiers;
            }
        };
        defineCenterLoc.setCityCenter(player);
        return player;
    }


    private static Configuration mockConfig(){
        return Unit.defaultConfig();
    }

    @BeforeEach
    public void setBefore(){
        Configuration config = mockConfig();
        territory = mockTerritory(10,10,config.max_dep());
        player1 = mockPlayer(territory.get(10));
        player2 = mockPlayer(territory.get(7));
        List<Region> regionList = new ArrayList<>(territory.size());
        regionList.addAll(territory);
        game = new ConfigGame(player1, player2, config, regionList);
        game.startTurn();
    }

    @AfterEach
    public void setAfter(){
        game.endTurn();
    }

    @Test
    public void testAttack(){
        // Test case 1: Player 1 attacks an enemy territory with a deposit of 100 and successfully captures it
        player1.budget = 1000;
        game.moveCityCrew(Position.of(0, 0));
        territory.get(10).updateOwner(player2);
        territory.get(6).updateDeposit(100);
        game.moveCityCrew(Position.of(1, 1));
        game.attack(DirectionNode.downright, 100);

        Assertions.assertEquals(899, game.budget());
        Assertions.assertNull(territory.get(6).getOwner());

        // Test case 2: Player 1 attacks an enemy territory with a deposit of 100, but fails to capture it due to insufficient budget
        player1.updateBudget(-899);
        territory.get(6).updateOwner(player2);
        game.attack(DirectionNode.downright, 100);

        Assertions.assertEquals(0, game.budget());
        Assertions.assertEquals(player2, territory.get(6).getOwner());

        // Test case 3: Player 1 attacks an enemy territory with a deposit of 10000, successfully captures it, and drains their entire budget
        player1.updateBudget(1000);
        territory.get(1).updateOwner(player2);
        territory.get(1).updateDeposit(10000);
        game.attack(DirectionNode.up, 10000);
        Assertions.assertEquals(999, game.budget());
        Assertions.assertEquals(player2, territory.get(1).getOwner());

        game.attack(DirectionNode.up, 999);
        Assertions.assertEquals(998, game.budget());

        game.attack(DirectionNode.up, 997);
        Assertions.assertEquals(0, game.budget());
        Assertions.assertEquals(player2, territory.get(1).getOwner());
        Assertions.assertEquals(9003, territory.get(1).getDeposit());

        // Test case 4: Player 1 attacks their own territory with a deposit of 100, which should be allowed
        player1.updateBudget(1000);
        territory.get(1).updateOwner(player1);
        territory.get(1).updateDeposit(100);
        game.attack(DirectionNode.up, 100);

        Assertions.assertEquals(899, game.budget());
        Assertions.assertEquals(player1, territory.get(1).getOwner());
        Assertions.assertEquals(9003, territory.get(1).getDeposit());
    }

    @Test
    public void testCollect(){
        SamplePlayer currentPlayer = player1;
        SampleRegion region = (SampleRegion) game.cityCrewRegion();

        // Test case 1: Player has zero budget and attempts to collect from a territory, which should fail.
        currentPlayer.budget = 0;
        assertFalse(game.collect(0));

        // Test case 2: Player has one budget and attempts to collect from a territory, which should succeed and deduct the deposit amount from their budget.
        currentPlayer.budget = 1;
        region.updateDeposit(100);
        assertTrue(game.collect(0));
        assertEquals(0, currentPlayer.getBudget());
        assertEquals(100, region.getDeposit());

        // Test case 3: Player has one budget and attempts to collect from an invalid territory, which should fail and leave their budget unchanged.
        currentPlayer.budget = 1;
        assertFalse(game.collect(-1));
        assertEquals(1, currentPlayer.getBudget());

        // Test case 4: City crew region has a deposit of 100, and the player has enough budget to collect from a territory. Collecting should succeed and deduct the deposit amount from the region and add it to the player's budget.
        region.updateDeposit(100);
        currentPlayer.budget = 2;
        assertTrue(game.collect(0));
        assertEquals(1, currentPlayer.budget);
        assertEquals(200, region.getDeposit());

        // Test case 5: Player attempts to collect from multiple territories, and the deposit amounts should be correctly deducted from the region and added to the player's budget.
        region.updateDeposit(200);
        currentPlayer.budget = 5;
        assertTrue(game.collect(0));
        assertTrue(game.collect(1));
        assertTrue(game.collect(2));
        assertEquals(5, currentPlayer.budget);
        assertEquals(397, region.getDeposit());

        // Test case 6: Player attempts to collect from an already empty territory, which should succeed but not affect the budget or deposit amount.
        currentPlayer.budget = 5;
        assertTrue(game.collect(2));
        assertEquals(6, currentPlayer.budget);
        assertEquals(395, region.getDeposit());
    }

    @Test
    public void testInvest(){
        // Test case 1: Player has one budget and invests in a territory with no adjacent owned player region, which should fail.
        SamplePlayer currentPlayer = player1;
        SampleRegion crewRegion = (SampleRegion) game.cityCrewRegion();
        game.moveCityCrew(Position.of(3, 3)); // no owned adjacent with 2 players
        currentPlayer.budget = 1;
        assertFalse(game.invest(0));
        assertEquals(0, currentPlayer.budget);
        assertEquals(0, crewRegion.deposit);

        // Test case 2: Player has enough budget to invest in a territory with an adjacent owned player region, which should succeed and deduct the correct amount from their budget and add it to the deposit of the territory.
        if (currentPlayer == player1) {
            game.moveCityCrew(Position.of(0, 0));
            crewRegion = territory.get(0);
        } else {
            game.moveCityCrew(Position.of(3, 0));
            crewRegion = territory.get(3);
        }

        currentPlayer.budget = 14;
        assertTrue(game.invest(12));
        assertEquals(1, currentPlayer.budget);
        assertEquals(12, crewRegion.deposit);

        // Test case 3: Player invests an amount equal to the deposit of the territory, which should succeed and deduct the correct amount from their budget and add it to the deposit of the territory.
        currentPlayer.budget = 100;
        assertTrue(game.invest(12));
        assertEquals(87, currentPlayer.budget);
        assertEquals(24, crewRegion.deposit);

        // Test case 4: Player invests an amount greater than the deposit of the territory, which should succeed and deduct the correct amount from their budget and add it to the deposit of the territory.
        currentPlayer.budget = 1000;
        assertTrue(game.invest(50));
        assertEquals(949, currentPlayer.budget);
        assertEquals(74, crewRegion.deposit);
    }

    @Test
    public void testRelocate(){
        long initialBudget = 100, distance;
        game.moveCityCrew(Position.of(3, 3));
        territory.get(15).updateOwner(player1); // x: 3, y: 3

        player1.budget = initialBudget;
        distance = 3;
        game.relocate();
        assertEquals(10 * distance + 1 + game.cost, initialBudget - game.budget() + 31);

        game.moveCityCrew(Position.of(0, 0));
        territory.get(0).updateOwner(player1); // x: 0, y: 0

        player1.budget = initialBudget;
        distance = 1;
        game.relocate();
        assertEquals(5 * distance + 10 + game.cost, initialBudget - game.budget());
    }

    @Test
    public void testMove(){
        // Set the player's budget and ensure they start at the crew location
        SamplePlayer currentPlayer = player1;
        currentPlayer.budget = 2;
        assertEquals(Position.of(0, 1), game.cityCrewRegion().getLocation());

        // Test moving up one tile
        assertTrue(game.move(DirectionNode.up));
        assertEquals(Position.of(0, 0), game.cityCrewRegion().getLocation());
        assertEquals(1, currentPlayer.budget);

        // Test attempting to move up again, which should true
        assertTrue(game.move(DirectionNode.up));
        assertEquals(Position.of(0, 0), game.cityCrewRegion().getLocation());
        assertEquals(0, currentPlayer.budget);

        // Test moving diagonally down-right to a new location
        currentPlayer.budget = 1;
        assertTrue(game.move(DirectionNode.downright));
        assertEquals(Position.of(1, 1), game.cityCrewRegion().getLocation());

        // Test moving in all directions
        currentPlayer.budget = 6;
        assertTrue(game.move(DirectionNode.upleft));
        assertEquals(Position.of(0, 0), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.downright));
        assertEquals(Position.of(1, 1), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.up));
        assertEquals(Position.of(1, 0), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.down));
        assertEquals(Position.of(1, 1), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.upright));
        assertEquals(Position.of(2, 0), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.downleft));
        assertEquals(Position.of(1, 1), game.cityCrewRegion().getLocation());

        // Test moving two tiles at a time in different directions
        currentPlayer.budget = 8;
        assertTrue(game.move(DirectionNode.downright));
        assertTrue(game.move(DirectionNode.downright));
        assertEquals(Position.of(3, 2), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.upleft));
        assertTrue(game.move(DirectionNode.upleft));
        assertEquals(Position.of(1, 1), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.down));
        assertTrue(game.move(DirectionNode.down));
        assertEquals(Position.of(1, 3), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.up));
        assertTrue(game.move(DirectionNode.up));
        assertEquals(Position.of(1, 1), game.cityCrewRegion().getLocation());

        // Test attempting to move into the opponent's region, which should fail
        currentPlayer.budget = 2;
        assertTrue(game.move(DirectionNode.downright));
        assertEquals(Position.of(2, 1), game.cityCrewRegion().getLocation());
        assertTrue(game.move(DirectionNode.upright));
        assertEquals(Position.of(3, 1), game.cityCrewRegion().getLocation());
        assertFalse(game.move(DirectionNode.upright));
        assertEquals(Position.of(3, 1), game.cityCrewRegion().getLocation());
        game.endTurn();
    }

    @Test
    public void testNearby(){
        player1.updateBudget(1000);
        game.moveCityCrew(Position.of(0, 0));

        assertEquals(0, game.nearby(DirectionNode.up));
        assertEquals(0, game.nearby(DirectionNode.upleft));
        assertEquals(0, game.nearby(DirectionNode.upright));
        assertEquals(0, game.nearby(DirectionNode.down));
        assertEquals(0, game.nearby(DirectionNode.downleft));
        assertEquals(0, game.nearby(DirectionNode.downright));
        territory.get(11).updateOwner(player2);
        assertEquals(101, game.nearby(DirectionNode.downright));
        territory.get(11).updateDeposit(100);
        assertEquals(103, game.nearby(DirectionNode.downright));
    }

    @Test
    public void testInterestPercentage(){
        Region playerAtRegion = territory.get(10);
        playerAtRegion.updateDeposit(1000);
        Configuration configuration = mockConfig();
        long playerDeposit = 1000;

        for (int i = 1; i <= 100; i++) {
            game.getConstructionPlan("done");
            game.getConstructionPlan("done");
            playerDeposit *= 1.0 + configuration.interest_pct(i, playerDeposit) / 100.0;
            assertEquals(Math.min(configuration.max_dep(), playerDeposit), // must not exceed limit
                    playerAtRegion.getDeposit(), String.format("not equals at turn %d", i));
        }
    }

    @Test
    public void lost_byNoBudget(){
        player1.budget = 100000;
        player2.budget = 100000;
        game.getConstructionPlan("done");
        game.getConstructionPlan("done");
        assertNull(game.getWin());

        player2.budget = 0;
        game.getConstructionPlan("done");
        assertThrows(GameException.GameEnded.class, () -> game.getConstructionPlan("done"));
        assertEquals(player1, game.getWin());
    }

    @Test
    public void lost_byCityCenter(){
        player1.budget = 99999;
        player2.budget = 99999;
        game.getConstructionPlan("done");
        game.getConstructionPlan("done");
        assertNull(game.getWin());

        territory.get(10).owner = null;
        game.getConstructionPlan("done");
        assertThrows(GameException.GameEnded.class, () -> game.getConstructionPlan("done"));
        assertEquals(player2, game.getWin());
    }
}
