package multithreading;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class test_3_19 {
    // 使用原子类，代替int
    // private static int count;
    private static final AtomicInteger count = new AtomicInteger(10000);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                // count++;
                count.getAndIncrement();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count.getAndIncrement();
            }
        });
        thread1.start();
        thread2.start();
        try{
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("count = " + count.get());
    }

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input a number:");
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num - 1 - i; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < i * 2 + 1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
