package Backend.Player;

import java.util.HashMap;
import java.util.Map;

public class ConfigPlayer implements player{
    protected long id;
    protected String name;
    protected long budget;
    protected Map<String, Long> identifier;

    public ConfigPlayer(long id, String name, long budget){
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.identifier = new HashMap<>();
    }

    @Override
    public long getID() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
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
        return identifier;
    }


    @Override
    public String toString(){
        return name;
    }
}
