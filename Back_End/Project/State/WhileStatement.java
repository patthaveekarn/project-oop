package Back_End.Project.State;


public class WhileStatement {
    private boolean condition;


    public boolean getCondition() {
        return condition;
    }

    private void checkloop(long input){
        int c=0;
        while (c>=0 && c <2){
            c++;
        }
    }

    public void Todowhile(long input){
        if (input >0){
            this.condition=true;
        }else {
            this.condition=false;
        }
    }



}
