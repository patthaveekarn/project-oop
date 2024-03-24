package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExecuteNode;

import java.util.List;

public class BlockStatementNode extends ExecuteNode {
    protected List<ExecuteNode> statements;

    public BlockStatementNode(List<ExecuteNode> statements){
        this.statements = statements;
    }

    @Override
    public boolean execute(Game bindings) {
        for(ExecuteNode statement : statements){
            if(!statement.execute(bindings)){
                return false;
            }
        }
        return true;
    }
}
