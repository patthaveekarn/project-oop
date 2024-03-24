package Back_End.Project.Statement;

public class IfStatementNode extends ConditionStatementNode{
    public IfStatementNode(ExpressionNode conditionNode, ExecuteNode trueStatement, ExecuteNode falseStatement) {
        super(conditionNode, trueStatement, falseStatement);

    }
}
