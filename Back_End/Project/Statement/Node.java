package Back_End.Project.Statement;


import Back_End.Project.GameProcess.Game;

public abstract class Node{
    public abstract static class ExpressionNode extends Node {
        public abstract long evaluate(Game bindings);
        public abstract String toString();
    }

    public abstract static class ExecuteNode extends Node {
        public ExecuteNode next;
        public abstract boolean execute(Game bindings);
    }

}
