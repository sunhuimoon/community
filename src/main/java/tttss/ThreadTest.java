package tttss;

import java.util.concurrent.TimeUnit;

public class ThreadTest {
    private static Object object = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                synchronized (object) {
                    System.out.println("t1 start object.wait(), time = " + System.currentTimeMillis() / 1000);
                    object.wait();
                    object.notify();
                    System.out.println("t1 after object.wait(), time = " + System.currentTimeMillis() / 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                synchronized (object) {
                    System.out.println("t2 start object.notify(), time = " + System.currentTimeMillis() / 1000);
                    object.notify();
                    System.out.println("t2 after object.notify(), time = " + System.currentTimeMillis() / 1000);
                }

                synchronized (object) {
                    System.out.println("t2  hold lock again, time = " + System.currentTimeMillis() / 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        t1.start();
    }


}
