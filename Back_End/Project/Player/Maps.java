package Back_End.Project.Player;

import Back_End.Project.Config.ConfigFile.Config;
import Back_End.Project.Player.Playerr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//
public class Maps {
    protected long [][] players;
    protected int width =Config.n; // n=15 , columns X
    protected int length=Config.m; //m=20 , rows Y
    public Maps(){
        players = new long[length][width];
    }
    public void Randomregion (ArrayList<Playerr.Player> playerList){
        Random random = new Random();
        for (Playerr.Player player : playerList) {
            int x, y;
            do {
                x = random.nextInt(width);
                y = random.nextInt(length);
            } while (players[y][x] != 0); // ตรวจสอบว่าตำแหน่งนี้ว่างหรือไม่
            AddPlayer(player, y, x);
        }
    }

    public void Showmap(){
        for (int i = 0; i < length; i++) {
           System.out.println(Arrays.toString(players[i]));
        }
    }

    public void AddPlayer(Playerr.Player p, int y, int x){
        //players[x][y] = p;
        p.XcityplayerList.add(x);
        p.YcityplayerList.add(y);
        p.setXcitycenter(x);
        p.setYcitycenter(y);
        p.setXplayer(x);
        p.setYplayer(y);
        players[y][x]=Config.init_center_dep;
//        p.show();
//        System.out.println(p.YcityplayerList +""+ p.XcityplayerList);

    }

    public void deposoitofregion(Playerr.Player player){
        //b * log10 d * ln t.
        long b=Config.interest_pct;
        long t=player.getCountturn();
        int loop = player.XcityplayerList.size();
        int intialloop=0;
        while (intialloop<loop){
            int listX=player.XcityplayerList.get(intialloop);
            int listY=player.YcityplayerList.get(intialloop);
            long d=getMap(listX,listY);
            long increase= (long) (b * (Math.log10(d)) * Math.log(t));
            long totalincrease=d+increase;
            setMap(listX,listY,totalincrease);
        }
    }


    public void setMap(int x, int y,long map) {
        this.players[y][x] = map;
    }

    public long getMap(int x, int y) {
        return players[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }


    public void RemovePlayer(Playerr.Player p){
        p.setInit_budget(0);
        p.YcityplayerList.removeAll(p.YcityplayerList);
        p.XcityplayerList.removeAll(p.XcityplayerList);
    }



}

