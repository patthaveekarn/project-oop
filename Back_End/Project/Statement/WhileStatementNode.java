package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;

public class WhileStatementNode extends ConditionStatementNode{
    protected int count = 0;

    public WhileStatementNode(ExpressionNode expressionNode, ExecuteNode statementNode) {
        super(expressionNode, statementNode, null);

        if(trueStatement == null){
            trueStatement = this;
        }
    }

    @Override
    public boolean execute(Game bindings){
        if(super.conditionNode.evaluate(bindings) > 0 && count < 1000){
            count++;
            if(!trueStatement.execute(bindings)){
                return false;
            }
            return execute(bindings);
        }
        return true;
    }
}
