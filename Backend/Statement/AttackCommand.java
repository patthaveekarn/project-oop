package Backend.Statement;

import Backend.GameProcess.Game;
import Backend.Statement.Node.*;

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
