package Back_End.Project.Player;
import java.util.Map;

public interface Player {
    long getID();
    String getName();
    long getBudget();
    boolean updateBudget(long amount);
    Map<String, Long> identifiers();
}
