package Back_End.Project.Player;
import java.util.Scanner;

public class TimeCount {
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.print("Input Time : ");
        int time = in.nextInt();
        for (int i = time; i >= 0; i--) {
            Thread.sleep(1000);
            System.out.print(i + "\n");
        }
        System.out.println("Success");
    }
}
