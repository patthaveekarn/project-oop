package Backend.Statement;

import Backend.GameProcess.Game;

public class ConditionStatementNode {
    private boolean condition;
    private Node.ExecuteNode trueStatement;
    private Node.ExecuteNode falseStatement;

    public ConditionStatementNode(boolean condition, Node.ExecuteNode trueStatement, Node.ExecuteNode falseStatement) {
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

