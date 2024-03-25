package Back_End.Project.Player;

import Back_End.Project.Config.ConfigFile.Config;
import java.util.concurrent.atomic.AtomicInteger;
//

public class Time {
    private int time;
    private boolean running;
    private Thread thread;

    public Time() {

    }
    public int MinToSec(int time){
        return time*60;
    }
    public int  start_intial() {
        this.running = true;
       // this.time=MinToSec(Back_End.Project.Config.init_plan_min);
        this.time=5;
        AtomicInteger t= new AtomicInteger();
        this.thread = new Thread(() -> {
            while (this.time > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.time--;
                t.set(time);
                System.out.println(this.time);
            }
            this.running = false;
        });
        this.thread.start();
        return t.get();
    }

    public void start_plan() {
        this.running = true;
        this.time=MinToSec(Config.plan_rev_min);
        this.thread = new Thread(() -> {
            while (this.time > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.time--;
                System.out.println(this.time);
            }
            this.running = false;
        });
        this.thread.start();
    }

    public void stop() {
        if (this.thread != null) {
            this.thread.interrupt();
        }
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }

    public int getTime() {
        return this.time;
    }
}