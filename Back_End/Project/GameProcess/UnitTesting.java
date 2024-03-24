package Back_End.Project.GameProcess;

import Back_End.Project.Region.Position;
import Back_End.Project.Region.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnitTesting {
    @Test
    public void testCheckLoadConfiguration() {
        Assertions.assertDoesNotThrow(() -> Unit.loadConfiguration("""
                rows = 50
                cols = 50
                init_plan_min = 1
                init_plan_sec = 1
                init_budget = 5000
                init_center_dep = 1000
                plan_rev_min = 1
                plan_rev_sec = 1
                rev_cost = 50
                max_dep = 200000
                interest_pct = 500
                """));

        Assertions.assertDoesNotThrow(() -> Unit.loadConfiguration("""
                rows = 50
                cols = 50
                init_budget = 5000
                init_center_dep = 1000
                rev_cost = 50
                max_dep = 200000
                interest_pct = 500
                """));

        Assertions.assertThrows(GameException.InvalidConfiguration.class, () -> Unit.loadConfiguration("""
                plan_rev_sec=60
                """));
    }

    @Test
    public void testCityCenter() {
        Game game = Unit.createGame("Player1", "Player2");
        List<Region> territory = game.getTerritory();
        List<Region> cityCenters = new ArrayList<>(2);

        for (Region region : territory) {
            if (region.isCityCenter()) cityCenters.add(region);
        }

        Assertions.assertEquals(2, cityCenters.size(), "more than two city centers created");
        Assertions.assertNotEquals(cityCenters.get(0), cityCenters.get(1), "city center collapse");
    }

    @Test
    public void testRegionLocation() {
        Game game = Unit.createGame("Player1", "Player2");
        List<Region> territory = game.getTerritory();
        Set<Position> regions = new HashSet<>();

        for (Region region : territory) {
            regions.add(region.getLocation());
        }

        Assertions.assertEquals(regions.size(), territory.size(), "Duplicated Region");
    }

    @Test
    public void createCustomGame() {
        Game game1 = Unit.createCustom("""
                rows=9999
                cols=9999
                """, "player1", "player2");

        Assertions.assertEquals(999*999, game1.getTerritory().size());
    }
}
