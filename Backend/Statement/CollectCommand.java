package Backend.Statement;

import Backend.GameProcess.Game;
import Backend.Statement.Node.ExecuteNode;

public class CollectCommand extends ExecuteNode {
    protected Node.ExpressionNode expressionNode;

    public CollectCommand(Node.ExpressionNode expressionNode){
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.collect(expressionNode.evaluate(bindings));
    }
}

