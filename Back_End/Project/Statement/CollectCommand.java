package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExecuteNode;

public class CollectCommand extends ExecuteNode {
    protected ExpressionNode expressionNode;

    public CollectCommand(ExpressionNode expressionNode){
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.collect(expressionNode.evaluate(bindings));
    }
}

