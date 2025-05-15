package multithreading;

public class test_3_6 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while(true){
                System.out.println("hello 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while(true){
                System.out.println("hello 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            while(true){
                System.out.println("hello 3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t3");
        thread3.start();

        while(true){
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
