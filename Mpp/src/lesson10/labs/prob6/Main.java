package lesson10.labs.prob6;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue q = new Queue();
        int count = 0;
        for (int i=0;i<1000;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    q.add("1");
                }
            });
            t.start();
            count ++;
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println(count);
        System.out.println(q.count);

        Queue q2 = new Queue();
        int count2 = 0;
        for (int i=0;i<500;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    q2.sync_add("1");
                }
            });
            t.start();
            count2 ++;
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println(count2);
        System.out.println(q2.count);
    }

}
