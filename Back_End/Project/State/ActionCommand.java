package Back_End.Project.State;

import Back_End.Project.Player.Maps;
import Back_End.Project.Player.Playerr;
//
/** action done or relocate
 */
public class ActionCommand {
    protected long moneycitycenter;
    protected long budgetplayer;
    protected int Xcitycenter;
    protected int Ycitycenter;
    protected int Xplayer;
    protected int Yplayer;
    protected long countturn;
    protected boolean turn;

    public long getCountturn() {
        return countturn;
    }

    public void setCountturn(long countturn) {
        this.countturn = countturn;
    }

    public void Done(Playerr.Player player) {
        turn=player.getturn();
        countturn=player.getCountturn()+1;
        turn= true;
        player.setCountturn((int) countturn);
        player.setturn(turn);


    }

    public void reset(Playerr.Player player) {
        turn=player.getturn();
        Xcitycenter=player.getXcitycenter();
        Ycitycenter=player.getYcitycenter();
        Xplayer=Xcitycenter;
        Yplayer=Ycitycenter;
        turn=false;
        player.setXplayer(Xplayer);
        player.setYplayer(Yplayer);
    }


    public void Relocate(Playerr.Player player , Maps map) {
        Xcitycenter = player.getXcitycenter();
        Ycitycenter =player.getYcitycenter();
        Xplayer=player.getXplayer();
        Yplayer=player.getYplayer();
        budgetplayer=player.getInit_budget();
        moneycitycenter=map.getMap(Xcitycenter,Ycitycenter);
        long shortpath = (long) Math.sqrt((Math.pow(Xcitycenter-Xplayer,2) + Math.pow(Ycitycenter-Yplayer,2)));
        long cost= 5 *shortpath+10;
        budgetplayer-=cost;
        map.setMap(Xplayer,Yplayer,moneycitycenter); // relocate
        map.setMap(Xcitycenter,Ycitycenter,0); // delete old citycenter
        Xcitycenter=Xplayer;
        Ycitycenter=Yplayer;
        player.setXplayer(Xcitycenter);
        player.setYplayer(Ycitycenter);
        Done(player);
    }

}
