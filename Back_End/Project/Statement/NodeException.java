package Back_End.Project.Statement;

public class NodeException extends RuntimeException {
    protected NodeException(){
        super();
    }

    protected NodeException(String msg){
        super(msg);
    }

    public static class LeftoverToken extends NodeException{
        public LeftoverToken(String token){
            super("Unexpected token: " + token);
        }
    }

    public static class UnknownOp extends NodeException{
        public UnknownOp(String op){
            super(String.format("%s is not an operator", op));
        }
    }

    public static class UndefinedIdentifier extends NodeException{
        public UndefinedIdentifier(String identifier){
            super(String.format("identifier '%s' is not defined", identifier));
        }
    }

    public static class IntegerRequire extends NodeException{
        public IntegerRequire(String identifier){
            super(String.format("integer required instead of '%s' ", identifier));
        }
    }
}
