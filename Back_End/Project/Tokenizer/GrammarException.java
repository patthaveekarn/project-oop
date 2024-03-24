package Back_End.Project.Tokenizer;

public class GrammarException extends RuntimeException{
    public GrammarException(String msg){
        super(msg);
    }

    public static class idleToken extends GrammarException{
        private static String tailing(String after) {
            if (after != null)
                return String.format(", after '%s'", after);
            else
                return "";
        }

        public idleToken(String after) {
            super("expected more token" + tailing(after));
        }
    }

    public static class BadChar extends GrammarException{
        public BadChar(char got) {
            super("Bad char " + got);
        }
    }
}
