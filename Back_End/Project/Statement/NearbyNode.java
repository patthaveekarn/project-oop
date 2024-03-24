package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExpressionNode;

public class NearbyNode extends ExpressionNode {
    protected DirectionNode direction;

    public NearbyNode(DirectionNode direction){
        this.direction = direction;
    }

    @Override
    public long evaluate(Game bindings) {
        return bindings.nearby(direction);
    }

    @Override
    public String toString() {
        return "nearby " + direction;
    }
}
