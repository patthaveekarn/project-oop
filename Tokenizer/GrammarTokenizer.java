package Tokenizer;

public class GrammarTokenizer implements Tokenizer {
    private int current;
    private int line;
    private final String constructionPlan;
    private String next, prev;

    public GrammarTokenizer(String constructionPlan) {
        this.constructionPlan = constructionPlan;
        current = 0;
        line = 1;

    }

    public String getConstructionPlan() {
        return constructionPlan;
    }
}