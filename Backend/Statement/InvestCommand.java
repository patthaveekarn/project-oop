package Backend.Statement;

import Backend.GameProcess.Game;
import Backend.Statement.Node.ExecuteNode;
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
