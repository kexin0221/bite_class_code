package multithreading;

import java.util.Scanner;

public class test_3_12 {
    private static int flag = 0;
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while(flag == 0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("t1线程结束");
        });
        Thread thread2 = new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("请输入flag的值:");
                flag = scanner.nextInt();
            }
        });
        thread1.start();
        thread2.start();
    }
}
