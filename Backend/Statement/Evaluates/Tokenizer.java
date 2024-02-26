package Backend.Statement.Evaluates;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    protected String generic;
    protected List<String> tokens;
    protected String nextToken;

    public Tokenizer(String generic){
        this.generic = generic;
        this.tokens = new ArrayList<>();

    }

    private void ComputeNext(){

    }

    public String Peek(){
        return nextToken;
    }

    public String Consume(){
        String result = nextToken;
        return result;
    }
    boolean Peek(String regex){
        if(Peek() != null){
            return true;
        }
        return false;
    }

    void Consume(String regex){
        Consume();
    }
}
