package Backend.GameProcess;

import Backend.Player.*;
import Backend.Region.*;
import Backend.Statement.*;

import java.util.List;
import java.util.Map;
public interface Game {
    List<Region> getTerritory();

    void getConstructionPlan(String constructionPlan);

    player getPlayer1();
    player getPlayer2();
    player getCurrentPlayer();
    player getWin();

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
