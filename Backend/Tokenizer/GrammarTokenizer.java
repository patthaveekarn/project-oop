package Backend.Tokenizer;

public class GrammarTokenizer implements Tokenizer {
    private int current;
    private int line;
    private final String constructionPlan;
    private String next, prev;

    public GrammarTokenizer(String constructionPlan){
        this.constructionPlan = constructionPlan;
        current = 0;
        line = 1;
        computeNext();
    }

    private void computeNext(){
        if (constructionPlan == null) return;
        StringBuilder sb = new StringBuilder();
        while (current < constructionPlan.length() && skipCharacter(constructionPlan.charAt(current))) {
            if (constructionPlan.charAt(current) == '\n')
                line++;
            if (constructionPlan.charAt(current) == '#')
                skipComment();
            else
                current++;
        }

        if (current == constructionPlan.length()) {
            prev = next;
            next = null;
            return;
        }
        char c = constructionPlan.charAt(current);
        if (Character.isDigit(c)) {
            while (current < constructionPlan.length() && Character.isDigit(constructionPlan.charAt(current))) {
                sb.append(constructionPlan.charAt(current));
                current++;
            }
        } else if (skipLetter(c) || c == '_') {
            while (current < constructionPlan.length() && skipLetter(constructionPlan.charAt(current))) {
                sb.append(constructionPlan.charAt(current));
                current++;
            }
        } else if ("()+-*/%^{}=".contains(String.valueOf(c))) {
            sb.append(constructionPlan.charAt(current));
            current++;
        } else {
            throw new GrammarException.BadChar(c);
        }
        prev = next;
        next = sb.toString();
    }

    private void skipComment(){
        while(current < constructionPlan.length() && constructionPlan.charAt(current) != '\n'){
            current++;
        }
    }

    private boolean skipCharacter(char c){
        return Character.isWhitespace(c) || c == '#' || c == '"';
    }

    private boolean skipLetter(char c){
        return Character.isLetter(c) || c == '_';
    }

    @Override
    public boolean hasNextToken(){
        return next != null;
    }

    @Override
    public String peek(){
        if(next == null) {
            throw new GrammarException.idleToken(prev);
        }
        return next;
    }

    @Override
    public boolean peek(String RegularExpression){
        if(!hasNextToken()){
            return false;
        }else {
            return next.equals(RegularExpression);
        }
    }

    @Override
    public String consume(){
        if(!hasNextToken()) {
            throw new GrammarException.idleToken(prev);
        }else{
            String result = next;
            computeNext();
            return result;
        }
    }

    @Override
    public boolean consume(String RegularExpression){
        if(!hasNextToken()){
            throw new GrammarException.idleToken(prev);
        }else{
            if(next.equals(RegularExpression)){
                computeNext();
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public int getNewline() {
        return line;
    }
}
