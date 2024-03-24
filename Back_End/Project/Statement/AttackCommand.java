package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExecuteNode;

public class AttackCommand extends ExecuteNode {
    protected ExpressionNode expressionNode;
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
