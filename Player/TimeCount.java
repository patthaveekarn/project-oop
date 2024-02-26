package Player;
import java.util.Scanner;

public class TimeCount {
    public static void main(String[] args) throws Exception {

        Scanner t = new Scanner(System.in);
        System.out.print("Input Time : ");
        int time = t.nextInt();
        for (int i = time; i >= 0; i--) {
            Thread.sleep(1000);
            System.out.print(i + " ");
        }
        Thread.sleep(1000);
        System.out.println("Success");
    }

}
