package Back_End.Project.State;
import Back_End.Project.Config.ConfigFile.Config;
import Back_End.Project.Player.Maps;
import Back_End.Project.Player.Playerr;

/** invest region | collect region and Interest rates deposit of region
 */
public class RegionCommand {
    int Xplayer;
    int Yplayer;
    long budgetplayer;
    private ActionCommand act;

    public RegionCommand(){
        this.act=new ActionCommand();
    }

    public int getYplayer() {
        return Yplayer;
    }

    public int getXplayer() {
        return Xplayer;
    }

    public long getBudget() {
        return budgetplayer;
    }

    public void setYplayer(int yplayer) {
        Yplayer = yplayer;
    }

    public void setXplayer(int xplayer) {
        Xplayer = xplayer;
    }

    public void setBudget(long budget) {
        this.budgetplayer = budget;
    }

    public void invest(long moneyinvest, Playerr.Player player, Maps map) {
        if(moneyinvest>=Config.max_dep){
           act.Done(player);
        }else {
            Yplayer=player.getYplayer();
            Xplayer=player.getXplayer();
            map.setMap(Xplayer,Yplayer,moneyinvest);
            player.getXcityplayerList().add(Xplayer);
            player.getYcityplayerList().add(Yplayer);// อนาเขตplayer
            budgetplayer= player.getInit_budget();
            budgetplayer-=moneyinvest;
            setBudget(budgetplayer);
            System.out.println(player.getYcityplayerList()+""+player.getYcityplayerList());
        }
        act.Done(player);
    }

    public void collect(Playerr.Player player, long withdraw, Maps map) {
        if(map.getMap(player.getXplayer(),player.getYplayer()) < withdraw){
            act.Done(player);
        }else {
            Yplayer=player.getYplayer();
            Xplayer=player.getXplayer();
            long money = map.getMap(player.getXplayer(),player.getYplayer()) - withdraw;
            map.setMap(Xplayer,Yplayer,money);
            budgetplayer= player.getInit_budget();
            budgetplayer+=withdraw;
            if (map.getMap(player.getXplayer(),player.getYplayer()) <= 0){
                map.setMap(Xplayer,Yplayer,0);
            }

        }
        act.Done(player);
    }

}
