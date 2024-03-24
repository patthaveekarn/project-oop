package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExecuteNode;

public class RelocateCommand extends ExecuteNode {
    public RelocateCommand(){
    }

    @Override
    public boolean execute(Game bindings) {
        bindings.relocate();
        return true;
    }
}
