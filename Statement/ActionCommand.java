package Statement;

import Player.player;

public class ActionCommand {
    private int Money;
    private int budget;
    protected int cityX;
    protected int cityY;
    protected int turn;
    protected double countturn;
    public double getCountturn(){
        return countturn;}
    public void setCountturn(long countturn) {
        this.countturn = countturn;
    }
}
