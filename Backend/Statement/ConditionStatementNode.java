package Backend.Statement;

public class ConditionStatementNode {
    private boolean condition;
    private ExecuteNode trueStatement;
    private ExecuteNode falseStatement;

    public ConditionStatementNode(boolean condition, ExecuteNode trueStatement, ExecuteNode falseStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }

    public boolean execute(Game bindings) {
        if (condition) {
            return trueStatement.execute(bindings);
        } else {
            return falseStatement.execute(bindings);
        }
    }
}

