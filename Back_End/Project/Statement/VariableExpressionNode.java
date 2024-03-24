package Back_End.Project.Statement;

import Back_End.Project.GameProcess.Game;
import Back_End.Project.Statement.Node.ExpressionNode;

public class VariableExpressionNode  extends ExpressionNode {
    protected long variable;
    protected String identifier;

    public VariableExpressionNode(long variable){
        this.variable = variable;
        this.identifier = null;
    }
    public VariableExpressionNode(String identifier){
        this.variable = 0;
        this.identifier = identifier;
    }

    @Override
    public long evaluate(Game bindings) {
        if(identifier == null){
            return variable;
        }else{
            Long configVariable = bindings.specialIdentifiers().get(identifier);
            if(configVariable != null){
                return configVariable;
            }
            Long variable = bindings.identifiers().get(identifier);
            if(variable == null){
                throw new NodeException.UndefinedIdentifier(identifier);
            }
            return variable;
        }
    }

    public long evaluate(){
        if(identifier == null){
            return variable;
        }else{
            throw new NodeException.IntegerRequire(identifier) {
            };
        }
    }

    @Override
    public String toString() {
        return identifier != null ? identifier : String.valueOf(variable);
    }
}
