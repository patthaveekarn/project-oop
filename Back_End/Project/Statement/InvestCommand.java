package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExecuteNode;


public class InvestCommand extends ExecuteNode {
    protected ExpressionNode expressionNode;

    public InvestCommand(ExpressionNode expressionNode){
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean execute(Game bindings) {
        return bindings.invest(expressionNode.evaluate(bindings));
    }

}
