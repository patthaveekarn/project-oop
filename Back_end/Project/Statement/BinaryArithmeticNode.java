package Back_end.Project.Statement;

import Back_end.Project.GameProcess.Game;
import Back_end.Project.Statement.Node.*;

public class BinaryArithmeticNode extends ExpressionNode {
    protected ExpressionNode left, right;
    protected String op;

    public BinaryArithmeticNode(ExpressionNode left, String op, ExpressionNode right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public long evaluate(Game bindings){
        long LeftValue = left.evaluate(bindings);
        long RightValue = right.evaluate(bindings);

        return switch (op) {
            case "+" -> LeftValue + RightValue;
            case "-" -> LeftValue - RightValue;
            case "*" -> LeftValue * RightValue;
            case "/" -> LeftValue / RightValue;
            case "%" -> LeftValue % RightValue;
            case "^" -> (long) Math.pow(LeftValue, RightValue);
            default -> throw new NodeException.UnknownOp(op);
        };
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", left.toString(), op, right.toString());
    }
}
