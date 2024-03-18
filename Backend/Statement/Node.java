package Backend.Statement;

public abstract class Node {

    public static abstract class ExpressionNode extends Node {
        public abstract long evaluate(Game bindings);
        public abstract String toString();
    }

    public static abstract class ExecuteNode extends Node {
        public ExecuteNode next;
        public abstract boolean execute(Game bindings);
    }

}
