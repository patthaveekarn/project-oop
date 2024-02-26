package Backend.Player;

import java.util.Map;

public interface player {
    long getID();
    String getName();
    long getBudget();
    boolean updateBudget(long amount);
    Map<String, Long> identifiers();
}
