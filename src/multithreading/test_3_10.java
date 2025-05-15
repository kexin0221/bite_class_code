package multithreading;

class Counter{
    private int count = 0;
//    public void add(){
//        synchronized (this){ //this指向counter
//            count++;
//        }
//    }

    synchronized public void add(){
        count++;
    }

    public int getCount(){
        return count;
    }
}

public class test_3_10 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++){
                counter.add();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++){
                counter.add();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("count = " + counter.getCount());
    }

    private static int count = 0;

    public static void main2(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            Thread cur = Thread.currentThread();
            for (int i = 0; i < 50000; i++) {
                synchronized (cur) {
                    count++;
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (thread1) {
                for (int i = 0; i < 50000; i++) {
                    count++;
                }
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(count);
    }

    public static void main1(String[] args) throws InterruptedException {
        Object locker = new Object();
        // Object locker2 = new Object();
        // String s = new String();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (locker) {
                    count++;
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (locker) {
                for (int i = 0; i < 50000; i++) {
                    count++;
                }
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
