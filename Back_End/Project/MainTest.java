package Back_End.Project;

import Back_End.Project.Player.Maps;
import Back_End.Project.Player.Playerr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MainTest {

    @Test
    void main() {

        ArrayList<Playerr.Player> playerList = new ArrayList<>();
        playerList.add(new Playerr.Player("TT"));
        playerList.add(new Playerr.Player("HH"));
        playerList.add(new Playerr.Player("ss"));
        Maps map = new Maps();
        Gamestate gameState = new Gamestate(map, playerList);
        map.AddPlayer(playerList.get(0),1,1);
        gameState.ReadConstruplan(playerList.get(0),map);
        map.Showmap();
//        map.AddPlayer(playerList.get(1),10,9);
//        map.AddPlayer(playerList.get(1),10,8);
//        map.Showmap();
//        Assertions.assertEquals(0,gameState.getInfoExpression().viewspace(playerList.get(0),map));
//        Assertions.assertEquals(0,gameState.getInfoExpression().nearby(playerList.get(0),map));

    }
}