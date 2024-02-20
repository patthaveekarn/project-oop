package Tokenizer;

public interface Tokenizer {
    boolean NextToken();
    String peek();
    boolean peek(String RegularExpression);
    String consume();
    boolean consume(String RegularExpression);
    int getNewline();
}
