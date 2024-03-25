package Back_End.Project;

import Back_End.Project.Player.Maps;
import Back_End.Project.Player.Playerr;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MainTest {

    @Test
    void main() {

        ArrayList<Playerr.Player> playerList = new ArrayList<>();
        playerList.add(new Playerr.Player("m1"));
        playerList.add(new Playerr.Player("p1"));
        Maps map = new Maps();
        Gamestate gameState = new Gamestate(map, playerList);
        map.AddPlayer(playerList.get(0),1,1);
        gameState.ReadConstruplan(playerList.get(0),map);
        map.Showmap();
//        map.AddPlayer(playerList.get(1),2,2);
//        map.AddPlayer(playerList.get(1),10,8);
//        map.Showmap();


    }
}