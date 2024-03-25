package Back_End.Project.State;

import Back_End.Project.Player.Playerr;

/** act move ,  up | down | upleft | upright | downleft | downright
 */
public class MoveCommand{
    protected int Yplayer;
    protected int Xplayer;
    protected long budgetplayer;
    private ActionCommand act;

    public MoveCommand(){
        this.act=new ActionCommand();
    }

    public int getYplayer() {
        return Yplayer;
    }
    public int getXplayer(){
        return Xplayer;
    }
    public long getBudget(){
        return budgetplayer;
    }
    public void setYplayer(int Yplayer){
        this.Yplayer=Yplayer;
    }

    public void setXplayer(int Xplayer) {
        this.Xplayer = Xplayer;
    }

    public void setBudget(long budgetplayer) {
        this.budgetplayer = budgetplayer;
    }


    public void MoveUp(Playerr.Player player) {
        if(player.getInit_budget()>0 && (player.getYplayer()>0 )){
            Yplayer=player.getYplayer()-1;
            budgetplayer=player.getInit_budget()-1;
            player.setYplayer(Yplayer);
            player.setInit_budget(budgetplayer);
        }else {
            act.Done(player);
        }
    }

    public void MoveUpLeft(Playerr.Player player) {
        if(player.getInit_budget()>0 && (player.getYplayer()>0 && player.getXplayer()>0)){
            Yplayer=player.getYplayer()-1;
            Xplayer=player.getXplayer()-1;
            budgetplayer=player.getInit_budget()-1;
            player.setYplayer(Yplayer);
            player.setXplayer(Xplayer);
            player.setInit_budget(budgetplayer);
        }else {
            act.Done(player);
        }

    }


    public void MoveUpRight(Playerr.Player player) {
        if(player.getInit_budget()>0 && (player.getYplayer()>0 && player.getXplayer()<15)){
            Yplayer=player.getYplayer()-1;
            Xplayer=player.getXplayer()+1;
            budgetplayer=player.getInit_budget()-1;
            player.setYplayer(Yplayer);
            player.setXplayer(Xplayer);
            player.setInit_budget(budgetplayer);
        }else {
            act.Done(player);
        }

    }


    public void MoveDown(Playerr.Player player) {
        if(player.getInit_budget()>0 && (player.getYplayer()<20)){
            Yplayer=player.getYplayer()+1;
            budgetplayer=player.getInit_budget()-1;
            player.setYplayer(Yplayer);
            player.setInit_budget(budgetplayer);
        }else {
            act.Done(player);
        }

    }


    public void MoveDownLeft(Playerr.Player player) {
        if(player.getInit_budget()>0 && (player.getYplayer()<20 && player.getXplayer()>0)){
            Yplayer=player.getYplayer()+1;
            Xplayer=player.getXplayer()-1;
            budgetplayer=player.getInit_budget()-1;
            player.setYplayer(Yplayer);
            player.setXplayer(Xplayer);
            player.setInit_budget(budgetplayer);
        }else {
            act.Done(player);
        }

    }


    public void MoveDownRight(Playerr.Player player) {
        if(player.getInit_budget()>0 && (player.getYplayer()<20 && player.getXplayer()<15)){
            Yplayer=player.getYplayer()+1;
            Xplayer=player.getXplayer()+1;
            budgetplayer=player.getInit_budget()-1;
            player.setYplayer(Yplayer);
            player.setXplayer(Xplayer);
            player.setInit_budget(budgetplayer);
        }else {
            act.Done(player);
        }
    }


}
