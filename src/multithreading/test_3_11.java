package multithreading;

public class test_3_11 {

    public static void main(String[] args) throws InterruptedException {
        Object locker1 = new Object();
        Object locker2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (locker1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (locker2){
                    System.out.println("t1两个锁都获取到");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (locker1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (locker2){
                    System.out.println("t2两个锁都获取到");
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private static int count = 0;

    public static void main1(String[] args) throws InterruptedException {
        Object locker = new Object();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (locker){
                    count++;
                }
            }
        });
        thread1.start();
        thread1.join();
        System.out.println("count = " + count);
    }
}
