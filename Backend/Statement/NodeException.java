package Backend.Statement;

public class NodeException extends RuntimeException {

    protected NodeException(String message) {
        super(message);
    }

    public static class LeftoverToken extends NodeException {
        public LeftoverToken(String token) {
            super("Unexpected token: " + token);
        }
    }

    public static class UnknownOp extends NodeException {
        public UnknownOp(String op) {
            super(String.format("'%s' is not a recognized operator", op));
        }
    }

    public static class UndefinedIdentifier extends NodeException {
        public UndefinedIdentifier(String identifier) {
            super(String.format("Identifier '%s' is not defined", identifier));
        }
    }

    public static class IntegerRequire extends NodeException {
        public IntegerRequire(String identifier) {
            super(String.format("Integer required instead of '%s'", identifier));
        }
    }
}
