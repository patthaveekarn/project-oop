package Backend.Statement;

public class MoveCommand extends ExecuteNode {
    protected DirectionNode direction;

    public MoveCommand(DirectionNode direction){
        this.direction = direction;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.move(direction);
    }
}