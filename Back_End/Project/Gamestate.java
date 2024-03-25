package Back_End.Project;

import Back_End.Project.Player.Maps;
import Back_End.Project.Player.Time;
import Back_End.Project.Player.Playerr;
import Back_End.Project.State.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gamestate {
    private Maps map;
    private ArrayList<Playerr.Player> players;
    private Playerr.Player p;
    private MoveCommand move;
    private ActionCommand act;
    private AttackCommand attack;
    private RegionCommand regionCommand;
    private InfoExpression infoExpression;
    private Time time;

    private WhileStatement whileStatement;

    private IfStatement ifStatement;

//
    public Gamestate(Maps map, ArrayList<Playerr.Player> players) {
        this.map = map;
        this.players = players;
        this.move=new MoveCommand();
        this.regionCommand=new RegionCommand();
        this.act=new ActionCommand();
        this.attack=new AttackCommand();
        this.infoExpression=new InfoExpression();
        this.p=new Playerr.Player();
        this.time=new Time();
        this.whileStatement=new WhileStatement();
        this.ifStatement=new IfStatement();
    }

    public void showlist(){
        System.out.println(getP().getXcityplayerList());
        System.out.println(getP().getYcityplayerList());
    }
    public ActionCommand getAct() {
        return act;
    }

    public Playerr.Player getP() {
        return p;
    }

    public Maps getMap() {
        return map;
    }
    public ArrayList<Playerr.Player> getPlayers() {
        return players;
    }

    public MoveCommand getMove(){
        return move;
    }

    public AttackCommand getAttack() {
        return attack;
    }

    public InfoExpression getInfoExpression() {
        return infoExpression;
    }

    public RegionCommand getRegionCommand() {
        return regionCommand;
    }

    public Time getTime() {
        return time;
    }

    public IfStatement getIfStatement() {
        return ifStatement;
    }

    public WhileStatement getWhileStatement() {
        return whileStatement;
    }

    public boolean GameOver(){
        if (map.getMap(p.getXcitycenter(), p.getYcitycenter())==0){
            return true;
        }else {
            return false;
        }
    }

    public void Constructionplans(Playerr.Player player){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Test something");

        String userName = myObj.nextLine();  // Read user input
        //System.out.println("Username is: " + userName);  // Output user input
        WriteConstruplan(userName);
    }


    public void WriteConstruplan(String text){
        try (FileWriter writer = new FileWriter("output.txt", true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter output = new PrintWriter(bw)) {
            output.println(text);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public void ReadConstruplan(Playerr.Player player, Maps map){
        Path file = Paths.get("output.txt");
        Charset charset = Charset.forName("US-ASCII");
        long deposit=0;
        long bedget=0;
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line;
            ArrayList<String> list =new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                list.removeAll(list);
                list.addAll(bracket(line));
                for(int i=list.size()-1;i>=0;i--){
                    String checktext=list.get(i);
                    if( checktext.equals("while")){
                        System.out.println(deposit);
                        getWhileStatement().Todowhile(10L);
//                        System.out.println("Wh");
                    }else if(checktext.equals("deposit")){ //deposit
                        int y=player.getYplayer();
                        int x=player.getXplayer();
                        deposit=map.getMap(x,y);
//                        System.out.println("De");
                    }else if(checktext.equals("budget")){
                        System.out.println("bedget");
                    }
                }


//                if ('+' == str[str.length - 1] || str[str.length - 1] == '-' || str[str.length - 1] == '*' || str[str.length - 1] == '/' || str[str.length - 1] == '%')
//                    throw new Exception("Some of the last string's input files contain operands that cannot be calculated !!");
//                else {
//                    //  Writefile();
//                }

            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> bracket(String input){
        String patternStr = "[{}()]";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);

        ArrayList<String> list = new ArrayList<String>();
        int lastIndex = 0;

        // วนลูปผ่าน match เพื่อคัดคำที่ไม่ใช่วงเล็บหรือปีกกา และเก็บใน ArrayList
        while (matcher.find()) {
            String word = input.substring(lastIndex, matcher.start()).trim();
            if (!word.isEmpty()) {
                list.add(word);
            }
            lastIndex = matcher.end();
        }

        // เพิ่มคำสุดท้ายลงใน ArrayList หากมี
        String lastWord = input.substring(lastIndex).trim();
        if (!lastWord.isEmpty()) {
            list.add(lastWord);
        }

        return list;

    }


}
