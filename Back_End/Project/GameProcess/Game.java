package Back_End.Project.GameProcess;

import Back_End.Project.Player.Player;
import Back_End.Project.Region.Position;
import Back_End.Project.Region.Region;
import Back_End.Project.Statement.DirectionNode;

import java.util.List;
import java.util.Map;
public interface Game {
    List<Region> getTerritory();

    void getConstructionPlan(String constructionPlan);

    Player getPlayer1();
    Player getPlayer2();
    Player getCurrentPlayer();
    Player getWin();

    Region regionOn(Position position);
    Region cityCrewRegion();

    boolean attack(DirectionNode direction, long totalValue);
    boolean collect(long totalValue);
    boolean invest(long totalValue);
    boolean relocate();
    boolean move(DirectionNode direction);

    Map<String, Long> identifiers();
    Map<String, Long> specialIdentifiers();

    long opponent();
    long nearby(DirectionNode direction);
    long budget();
    long getTurn();

}
