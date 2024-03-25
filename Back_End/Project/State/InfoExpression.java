package Back_End.Project.State;

import Back_End.Project.Player.Maps;
import Back_End.Project.Player.Playerr;

import java.util.ArrayList;
import java.util.Collections;

/**
 * check nearby  function | opponent expression view space
 */
public class InfoExpression{

    protected int Xcitycenter;
    protected int Ycitycenter;
    protected int Xplayer;
    protected int Yplayer;

    /**
     * view for Player
     */
//
    public long viewspace(Playerr.Player player, Maps map) {// opponent exprssion
        Xplayer = player.getXplayer();
        Yplayer = player.getYplayer();
        ArrayList<Long> index = new ArrayList<Long>();
        // up
        for (int i = 0; i < 6; i++) {
            int Xcheck = Xplayer;
            int Ycheck = Yplayer;
            int checkconditionY = Ycheck - (i + 1);
            if (checkconditionY > 0 && checkconditionY < 20) {
                Ycheck -= (i + 1);
            } else {
                break;
            }
            long check = map.getMap(Xcheck, Ycheck);

            if (player.getYcityplayerList().contains(Ycheck)) {
                break;
            } else {
                if (check > 0) {
                    index.add(Long.valueOf((10 * (i + 1)) + 1));
                }
            }
        }
        // upright
        for (int i = 0; i < 6; i++) {
            int Xcheck = Xplayer;
            int Ycheck = Yplayer;
            int checkconditionY = Ycheck - (i + 1);
            int checkconditionX = Xcheck + (i + 1);
            if (checkconditionY > 0 && checkconditionY < 20) {
                Ycheck -= (i + 1);
            } else {
                break;
            }
            if (checkconditionX < 15 && checkconditionX > 0) {
                Xcheck += (i + 1);
            } else {
                break;
            }
            long check = map.getMap(Xcheck, Ycheck);

            if (player.getXcityplayerList().contains(Xcheck) && player.getYcityplayerList().contains(Ycheck)) {
                break;
            } else {
                if (check > 0) {
                    index.add(Long.valueOf((10 * (i + 1)) + 2));
                }
            }

        }
        // downright
        for (int i = 0; i < 6; i++) {
            int Xcheck = Xplayer;
            int Ycheck = Yplayer;
            int checkconditionY = Ycheck + (i + 1); //15
            int checkconditionX = Xcheck + (i + 1); //15
            if (checkconditionY > 0 && checkconditionY < 20) {
                Ycheck += (i + 1);
            } else {
                break;
            }
            if (checkconditionX < 15 && checkconditionX > 0) {
                Xcheck += (i + 1);
            } else {
                break;
            }
            long check = map.getMap(Xcheck, Ycheck);

            if (player.getXcityplayerList().contains(Xcheck) && player.getYcityplayerList().contains(Ycheck)) {
                break;
            } else {
                if (check > 0) {
                    index.add(Long.valueOf((10 * (i + 1)) + 3));
                }
            }


        }
        // down
        for (int i = 0; i < 6; i++) {
            int Xcheck = Xplayer;
            int Ycheck = Yplayer;
            int checkconditionY = Ycheck + (i + 1);
            if (checkconditionY > 0 && checkconditionY < 20) {
                Ycheck += (i + 1);
            } else {
                break;
            }
            long check = map.getMap(Xcheck, Ycheck);

            if (player.getYcityplayerList().contains(Ycheck)) {
                break;
            } else {
                if (check > 0) {
                    index.add(Long.valueOf((10 * (i + 1)) + 4));
                }

            }


        }
        // downleft
        for (int i = 0; i < 6; i++) {
            int Xcheck = Xplayer;
            int Ycheck = Yplayer;
            int checkconditionY = Ycheck + (i + 1);
            int checkconditionX = Xcheck - (i + 1);
            if (checkconditionY > 0 && checkconditionY < 20) {
                Ycheck += (i + 1);
            } else {
                break;
            }
            if (checkconditionX < 15 && checkconditionX > 0) {
                Xcheck -= (i + 1);
            } else {
                break;
            }
            long check = map.getMap(Xcheck, Ycheck);

            if (player.getXcityplayerList().contains(Xcheck) && player.getYcityplayerList().contains(Ycheck)) {
                break;
            } else {
                if (check > 0) {
                    index.add(Long.valueOf((10 * (i + 1)) + 5));
                }

            }


        }
        // upleft
        for (int i = 0; i < 6; i++) {
            int Xcheck = Xplayer;
            int Ycheck = Yplayer;
            int checkconditionY = Ycheck - (i + 1);
            int checkconditionX = Xcheck - (i + 1);
            if (checkconditionY > 0 && checkconditionY < 20) {
                Ycheck -= (i + 1);
            } else {
                break;
            }
            if (checkconditionX < 15 && checkconditionX > 0) {
                Xcheck -= (i + 1);
            } else {
                break;
            }
            long check = map.getMap(Xcheck, Ycheck);

            if (player.getXcityplayerList().contains(Xcheck) && player.getYcityplayerList().contains(Ycheck)) {
                break;
            } else {
                if (check > 0) {
                    index.add(Long.valueOf((10 * (i + 1)) + 6));
                }

            }


        }


        if (index.size() == 0) {
            index.add(Long.valueOf(0));
        }
        long mincheck = Collections.min(index);


        return mincheck;
    }


    public long nearby(Playerr.Player player, Maps map) {
        ArrayList<Long> listnearby = new ArrayList<Long>();
        Xplayer = player.getXplayer();
        Yplayer = player.getYplayer();
        int Xcheck = Xplayer;
        int Ycheck = Yplayer;
        boolean checkprocess = false;

        //up
        if (Ycheck < 20 && Ycheck > 0) { // y intial 0-20
            Ycheck--;
            checkprocess = true;
        }
        listnearby.add(onedirectnearby(player, map, Xcheck, Ycheck, 1));
        if (checkprocess == true) {
            Ycheck++;
            checkprocess = false;
        }

        //upright
        if (Ycheck < 20 && Ycheck > 0 && Xcheck < 15 && Xcheck > 0) { // y intial 0-20 and x intail 0-20
            Ycheck--;
            Xcheck++;
            checkprocess = true;
        }
        listnearby.add(obliquenearby(player, map, Xcheck, Ycheck, 2));
        if (checkprocess == true) {
            Ycheck++;
            Xcheck--;
            checkprocess = false;
        }

        //downright
        if (Ycheck < 20 && Ycheck > 0 && Xcheck < 15 && Xcheck > 0) { // y intial 0-20 and x intail 0-20
            Ycheck++;
            Xcheck++;
            checkprocess = true;
        }
        listnearby.add(obliquenearby(player, map, Xcheck, Ycheck, 3));
        if (checkprocess == true) {
            Ycheck--;
            Xcheck--;
            checkprocess = false;
        }

        //down
        if (Ycheck < 20 && Ycheck > 0) { // y intial 0-20
            Ycheck++;
            checkprocess = true;
        }
        listnearby.add(onedirectnearby(player, map, Xcheck, Ycheck, 4));
        if (checkprocess == true) {
            Ycheck--;
            checkprocess = false;
        }

        //downleft
        if (Ycheck < 20 && Ycheck > 0 && Xcheck < 15 && Xcheck > 0) { // y intial 0-20 and x intail 0-20
            Ycheck++;
            Xcheck--;
            checkprocess = true;
        }
        listnearby.add(obliquenearby(player, map, Xcheck, Ycheck, 5));
        if (checkprocess == true) {
            Ycheck--;
            Xcheck++;
            checkprocess = false;
        }

        //upleft
        if (Ycheck < 20 && Ycheck > 0 && Xcheck < 15 && Xcheck > 0) { // y intial 0-20 and x intail 0-20
            Ycheck--;
            Xcheck--;
            checkprocess = true;
        }
        listnearby.add(obliquenearby(player, map, Xcheck, Ycheck, 6));
        if (checkprocess == true) {
            --Ycheck;
            --Xcheck;
            checkprocess = false;
        }

        long mincheck = 0;

        System.out.println(listnearby);
        for (int i = 0; i < listnearby.size(); i++) {

            if (listnearby.get(i) > 0) {
                mincheck = listnearby.get(i);
                break;
            } else {
                mincheck = 0;
            }
        }

        return mincheck;
    }

    public long onedirectnearby(Playerr.Player player, Maps map, int xposition, int yposition, int direct) {
        long checknearby = map.getMap(xposition, yposition);
        long near = 0;
//        System.out.println(player.YcityplayerList);
//        System.out.println(yposition);

        if (player.getYcityplayerList().contains(yposition)) {

        }else {
            if (checknearby > 0) {
                long y = countdigital(checknearby); //y=digitalnumber
                near = 100 * direct + y;
            } else {
                near = 0;
            }
        }

        return near;
    }

    public long obliquenearby(Playerr.Player player, Maps map, int xposition, int yposition, int direct) {
        long checknearby = map.getMap(xposition, yposition);
        long near = 0;
        if ((player.getYcityplayerList().contains(yposition) && player.getXcityplayerList().contains(xposition))) {

        } else {
            if (checknearby > 0) {
                long y = countdigital(checknearby); //y=digitalnumber
                near = 100 * direct + y;
            } else {
                near = 0;
            }
        }

        return near;
    }

    public long countdigital(long number) {
        long count = 0;
        while (number != 0) {
            number /= 10;
            count++;
        }
        return count;
    }


//    public static void main(String[] args){
//
//
//    }
}
