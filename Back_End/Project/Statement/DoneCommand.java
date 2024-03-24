package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExecuteNode;

public class DoneCommand extends ExecuteNode {
    public DoneCommand(){
    }

    @Override
    public boolean execute(Game bindings) {
        return false;
    }
}
