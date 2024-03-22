package Backend.Statement;

import Backend.GameProcess.Game;

public class VariableExpressionNode extends Node.ExpressionNode {
    protected long variable;
    protected String identifier;

    public VariableExpressionNode(long variable) {
        this.variable = variable;
        this.identifier = null;
    }

    public VariableExpressionNode(String identifier) {
        this.variable = 0;
        this.identifier = identifier;
    }

    @Override
    public long evaluate(Game bindings) {
        if (identifier == null) {
            return variable;
        } else {
            Long configVariable = bindings.specialIdentifiers().get(identifier);
            if (configVariable != null) {
                return configVariable;
            }
            Long variableValue = bindings.identifiers().get(identifier);
            if (variableValue == null) {
                throw new NodeException.UndefinedIdentifier(identifier);
            }
            return variableValue;
        }
    }

    // This method is not necessary and can lead to confusion.
    // It's better to always evaluate expressions with respect to game bindings.
    // Keeping it can lead to unexpected behavior or errors.
    // public long evaluate() {
    //     if (identifier == null) {
    //         return variable;
    //     } else {
    //         throw new NodeException.IntegerRequire(identifier) {
    //         };
    //     }
    // }

    @Override
    public String toString() {
        return identifier != null ? identifier : String.valueOf(variable);
    }
}
