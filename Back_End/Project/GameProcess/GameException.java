package Back_End.Project.GameProcess;

import Back_End.Project.Statement.NodeException;

public class GameException extends RuntimeException {
    public static class NotImplemented extends NodeException {
        public NotImplemented() {
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement e = stacktrace[2];
            String className = e.getClassName();
            String methodName = e.getMethodName();
            throw new NotImplemented(String.format("%s.%s not implemented", className, methodName));
        }

        private NotImplemented(String m) {
            super(m);
        }
    }

    public static class InvalidConfiguration extends NodeException {
        public InvalidConfiguration() {
            super();
        }
    }

    public static class GameEnded extends NodeException {
        public GameEnded() {
            super("game already ended");
        }

    }
}
