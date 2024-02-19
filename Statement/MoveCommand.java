package Statement;

import Statement.Evaluates.Node;

public class MoveCommand implements Node {

    protected String direction;


    public MoveCommand(String direction){
        this.direction = direction;
    }
    public void up(){

    }
    public void down(){

    }
    public void upleft(){

    }
    public void upright(){

    }
    public void downleft(){

    }
    public void downright(){

    }

    @Override
    public double Evaluate() {
        if(up();){
            // move command

            // set direction
        }
        return 0;
    }

    @Override
    public void Print(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        System.out.println();
    }
}
