package Backend.Statement;

import Backend.GameProcess.Game;
import Backend.Statement.Node.ExecuteNode;

import java.util.List;

import java.util.List;

public class BlockStatementNode {
    private List<ExecuteNode> statements;

    public BlockStatementNode(List<ExecuteNode> statements) {
        this.statements = statements;
    }

    public boolean execute(Game bindings) {
        for (ExecuteNode statement : statements) {
            if (!statement.execute(bindings)) {
                // Handle error here
                System.err.println("Execution failed for statement: " + statement);
                return false;
            }
        }
        return true;
    }

    public boolean execute1(Game bindings) {
        return statements.stream().allMatch(statement -> statement.execute(bindings));
    }
}
