package Backend.Statement;

import Backend.GameProcess.Game;

public class WhileStatementNode extends ConditionStatementNode {
    protected int count = 0;

    public WhileStatementNode(ExpressionNode expressionNode, Node.ExecuteNode statementNode) {
        super(expressionNode, statementNode, null);

        // Ensure the trueStatement points to itself if not provided
        if (trueStatement == null) {
            trueStatement = this;
        }
    }

    @Override
    public boolean execute(Game bindings) {
        // Evaluate the condition node
        if (super.conditionNode.evaluate(bindings) > 0 && count < 1000) {
            count++; // Increment count to avoid infinite loops

            // Execute the statement node
            if (!trueStatement.execute(bindings)) {
                return false; // If execution fails, return false
            }

            // Recursively execute while loop
            return execute(bindings);
        }
        return true; // Return true when the condition becomes false or loop limit reached
    }
}
