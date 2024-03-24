package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExpressionNode;

public class OpponentNode  extends ExpressionNode {
    public OpponentNode(){

    }
    @Override
    public long evaluate(Game bindings) {
        return bindings.opponent();
    }

    @Override
    public String toString() {
        return "opponent";
    }
}
