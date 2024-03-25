package Back_End.Project.GameProcess;

import Back_End.Project.Parse.Parser;
import Back_End.Project.Parse.ProcessParse;
import Back_End.Project.Player.Player;
import Back_End.Project.Region.Position;
import Back_End.Project.Region.Region;
import Back_End.Project.Statement.DirectionNode;
import Back_End.Project.Statement.Node.ExecuteNode;
import Back_End.Project.Tokenizer.GrammarTokenizer;

import java.awt.geom.Point2D;
import java.util.*;

public class ConfigGame implements Game {
    protected final Player player1, player2;
    protected final Configuration config;
    protected final List<Region> territory;

    protected Player currentPlayer;
    protected Player win;
    protected Region cityCrew;

    protected long turn;
    protected final int cost = 1;
    protected final Map<Player, Region> cityCenterOfRegion;

    public ConfigGame(Player player1, Player player2, Configuration config, List<Region> territory) {
        this.player1 = player1;
        this.player2 = player2;
        this.config = config;
        this.territory = territory;
        this.currentPlayer = this.player1;
        this.cityCenterOfRegion = new HashMap<>();
        this.turn = 1;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Player getWin() {
        return win;
    }

    private Player winner() {
        if (player1.getBudget() == 0) {
            return player2;
        } else if (player2.getBudget() == 0) {
            return player1;
        }
        if (territory.stream().noneMatch(region -> region.isCityCenter() && region.getOwner() == player1))
            return player2;
        else if (territory.stream().noneMatch(region -> region.isCityCenter() && region.getOwner() == player2))
            return player1;
        return null;
    }

    @Override
    public List<Region> getTerritory() {
        return territory;
    }

    @Override
    public void getConstructionPlan(String constructionPlan) {
        if (win != null) {
            throw new GameException.GameEnded();
        }
        startTurn();
        executeConstructionPlan(constructionPlan);
        win = winner();
        endTurn();
    }

    private void executeConstructionPlan(String constructionPlan) {
        Parser parse = new ProcessParse(new GrammarTokenizer(constructionPlan));
        List<ExecuteNode> plans = parse.parse();
        for (ExecuteNode plan : plans) {
            plan.execute(this);
        }
    }

    private List<Region> getAdjacentRegions(Region region) {
        List<Region> adjacentRegions = new ArrayList<>(6);
        Position curLoc = region.getLocation();
        for (DirectionNode direction : DirectionNode.values()) {
            Position newLoc = curLoc.direction(direction);
            if (!newLoc.Check_isValidPosition(config.rows(), config.cols())) {
                continue;
            }
            adjacentRegions.add(regionOn(newLoc));
        }
        return adjacentRegions;
    }

    private void interestProcess() {
        for (Region region : territory) {
            if (region.getOwner() != null) {
                long deposit = region.getDeposit();
                deposit *= config.interest_pct(turn, deposit) / 100.0;
                region.updateDeposit(deposit);
            }
        }
    }

    @Override
    public Region regionOn(Position position) {
        long i = position.getPosY() * config.cols() + position.getPosX();
        return territory.get((int) i);
    }

    @Override
    public Region cityCrewRegion() {
        return cityCrew;
    }

    private void getCityCenters() {
        for (Region region : territory) {
            if (region.isCityCenter())
                cityCenterOfRegion.put(region.getOwner(), region);
        }
    }

    public void moveCityCrew(Position position) {
        if (!position.Check_isValidPosition(config.rows(), config.cols())) {
            return;
        }
        cityCrew = regionOn(position);
    }

    @Override
    public long getTurn() {
        return turn;
    }

    public void startTurn() {
        getCityCenters();
        cityCrew = cityCenterOfRegion.get(currentPlayer);
    }

    public void endTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
            interestProcess();
            turn++;
        }
    }

    @Override
    public Map<String, Long> identifiers() {
        return currentPlayer.identifiers();
    }

    @Override
    public Map<String, Long> specialIdentifiers() {
        Map<String, Long> bindings = new HashMap<>();
        bindings.put("rows", config.cols());
        bindings.put("cols", config.rows());
        bindings.put("currentRow", cityCrew.getLocation().getPosX());
        bindings.put("currentCol", cityCrew.getLocation().getPosY());
        bindings.put("budget", currentPlayer.getBudget());
        bindings.put("deposit", cityCrew.getDeposit());
        bindings.put("maxDeposit", config.max_dep());
        bindings.put("int", (long) config.interest_pct(turn, cityCrew.getDeposit()));
        bindings.put("random", new Random().nextLong(1000));
        return bindings;
    }

    private record FinalPosition(Position position, Position finalPos) implements Comparable<FinalPosition> {
        @Override
        public int compareTo(FinalPosition o) {
            return (int) (getShortestPath(position, finalPos) - getShortestPath(o.position, finalPos));
        }
    }

    private long CalculateShortestPathStar(Position PStart, Position PEnd) {
        PriorityQueue<FinalPosition> openSet = new PriorityQueue<>();
        HashMap<Position, Position> cameFrom = new HashMap<>();
        HashMap<Position, Double> gScore = new HashMap<>();
        openSet.add(new FinalPosition(PStart, PEnd));
        gScore.put(PStart, 0.0);

        while (!openSet.isEmpty()) {
            Position cur = openSet.remove().position();
            if (cur.equals(PEnd)) {
                return getShortestPathStar(cameFrom, cur);
            }

            for (DirectionNode direction : DirectionNode.values()) {
                Position neighbor = cur.direction(direction);
                if (!neighbor.Check_isValidPosition(config.rows(), config.cols()) || neighbor.equals(PStart)) {
                    continue;
                }

                double tentativeGScore = gScore.get(cur) + getShortestPath(cur, neighbor);
                gScore.putIfAbsent(neighbor, Double.POSITIVE_INFINITY);

                if (tentativeGScore >= gScore.get(neighbor)) {
                    continue;
                }
                cameFrom.put(neighbor, cur);
                gScore.put(neighbor, tentativeGScore);
                FinalPosition position = new FinalPosition(neighbor, PEnd);

                if (!openSet.contains(position)) {
                    openSet.add(position);
                }
            }
        }
        return -1;
    }

    private long getShortestPathStar(HashMap<Position, Position> cameFrom, Position cur) {
        long distance = 0;
        cur = cameFrom.get(cur);

        while (cur != null) {
            distance++;
            cur = cameFrom.get(cur);
        }
        return distance;
    }

    private static double getShortestPath(Position PStart, Position PEnd) {
        return Point2D.distance(PStart.getPosX(), PEnd.getPosX(), PStart.getPosY(), PEnd.getPosY());
    }

    @Override
    public boolean attack(DirectionNode direction, long totalValue) {
        if (totalValue + cost > currentPlayer.getBudget() || totalValue < 0) {
            currentPlayer.updateBudget(-cost);
            return false;
        }


        Position cityCrewShowLocation = cityCrew.getLocation();
        Position targetLocation = cityCrewShowLocation.direction(direction);

        if (targetLocation.Check_isValidPosition(config.rows(), config.cols())) {
            Region targetRegion = regionOn(targetLocation);

            if (totalValue < targetRegion.getDeposit()) {
                // update budget
                currentPlayer.updateBudget(-cost - totalValue);
                // update deposit
                targetRegion.updateDeposit(-totalValue);
            } else if (totalValue >= targetRegion.getDeposit()) {
                targetRegion.updateDeposit(-totalValue);
                targetRegion.updateOwner(null);
                currentPlayer.updateBudget(-cost - totalValue);
            }
        }
        return true;
    }

    @Override
    public boolean collect(long totalValue) {
        if (currentPlayer.getBudget() < 1 || totalValue < 0) {
            return false;
        }
        currentPlayer.updateBudget(-cost);
        Region targetRegion = cityCrew;
        if (totalValue > targetRegion.getDeposit()) {
            return true;
        }
        targetRegion.updateDeposit(-totalValue);
        currentPlayer.updateBudget(totalValue);
        if (targetRegion.getDeposit() == 0) {
            targetRegion.updateOwner(null);
        }
        return true;
    }

    @Override
    public boolean invest(long totalValue) {
        currentPlayer.updateBudget(-cost);

        // Check if the player has enough budget
        if (currentPlayer.getBudget() < cost) {
            return false;
        }

        // Check adjacency requirement
        boolean leastOneAdjacent = cityCrew.getOwner() == currentPlayer;
        for (Region adjacent : getAdjacentRegions(cityCrew)) {
            if (leastOneAdjacent) {
                break;
            }
            leastOneAdjacent = adjacent.getOwner() == currentPlayer;
        }
        if (!leastOneAdjacent) {
            return false;
        }

        // Check budget requirement
        if (currentPlayer.getBudget() < totalValue + 1) {
            return false;
        }
        currentPlayer.updateBudget(-totalValue);
        cityCrew.updateOwner(currentPlayer);
        cityCrew.updateDeposit(totalValue);
        return true;
    }

    @Override
    public boolean relocate() {
        // check has enough budget
        if (!currentPlayer.updateBudget(-cost)) {
            return false;
        }

        Position curCityCrewLoc = cityCrew.getLocation();
        Position curCityCenter = cityCenterOfRegion.get(currentPlayer).getLocation();
        long path = CalculateShortestPathStar(curCityCrewLoc, curCityCenter);
        long cost = 5 * path + 10;

        // execute if player has enough budget
        if (currentPlayer.getBudget() >= cost && cityCrew.getOwner() == currentPlayer) {
            currentPlayer.updateBudget(-cost);
            cityCrew.setCityCenter(currentPlayer);
            cityCenterOfRegion.get(currentPlayer).changeCityCenter();
        }
        return false;
    }

    @Override
    public boolean move(DirectionNode direction) {
        if (currentPlayer.getBudget() < cost) {
            return false;
        }
        currentPlayer.updateBudget(-cost);

        Position newLoc = cityCrew.getLocation().direction(direction);
        if (newLoc.Check_isValidPosition(config.rows(), config.cols())) {
            Region newRegion = regionOn(newLoc);
            if (newRegion.getOwner() == null || newRegion.getOwner() == currentPlayer) {
                cityCrew = newRegion;
            }
        }
        return true;
    }

    @Override
    public long opponent() {
        Position[] path = new Position[6];
        int distance = 0;
        boolean cutoff;

        for (int i = 0; i < 6; i++) {
            path[i] = cityCrew.getLocation();
        }
        do {
            for (int i = 0; i < 6; i++) {
                if (path[i] == null) {
                    continue;
                }

                long index = path[i].getPosY() * config.cols() + path[i].getPosX();
                Player owner = territory.get((int) index).getOwner();
                if (owner != null && owner != currentPlayer) {
                    // need modify
                    return i + 1L + distance * 10L;
                }
                path[i] = path[i].direction(DirectionNode.values()[i]);
            }

            for (int i = 0; i < 6; i++) {
                if (path[i] == null) {
                    continue;
                }
                path[i] = path[i].Check_isValidPosition(config.rows(), config.cols()) ? path[i] : null;
            }
            cutoff = true;

            for (Position position : path) {
                cutoff &= position == null;
            }
            distance++;

        } while (!cutoff);
        return 0;
    }

    @Override
    public long nearby(DirectionNode direction) {
        Position curLoc = cityCrew.getLocation();
        int distance = 0;
        Position newLoc = curLoc.direction(direction);

        while (newLoc.Check_isValidPosition(config.rows(), config.cols())) {
            Region region = regionOn(newLoc);

            if (region.getOwner() != null && region.getOwner() != currentPlayer) {
                // need modify
                return ((distance + 1L) * 100 + (long) (Math.log10(region.getDeposit() + 1)) + 1);
            }
            distance++;
            newLoc = newLoc.direction(direction);
        }
        return 0;
    }

    @Override
    public long budget() {
        return currentPlayer.getBudget();
    }
}
