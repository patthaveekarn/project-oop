package Back_end.Project.Statement;

import Back_end.Project.GameProcess.Game;
import Back_end.Project.Statement.Node.*;

public class AttackCommand extends ExecuteNode {
    protected Node.ExpressionNode expressionNode;
    protected DirectionNode direction;

    public AttackCommand(DirectionNode direction, ExpressionNode expressionNode){
        this.direction = direction;
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.attack(direction, expressionNode.evaluate(bindings));
    }
}
