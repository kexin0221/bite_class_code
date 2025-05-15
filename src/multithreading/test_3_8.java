package multithreading;

public class test_3_8 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
            System.out.println("t1结束");
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
            System.out.println("t2结束");
        });

        //正解！！
        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        System.out.println(count); //输出100000
    }

    public static void main4(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                System.out.println("开始等待main");
                mainThread.join(1000);
                System.out.println("结束等待main");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(3000);
        System.out.println("main结束");
    }

    private static int count = 0;
    public static void main3(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
        });
        //代码多线程并发执行产生的bug
        thread1.start();
        thread2.start();

        //预期结果：count=100000
        //System.out.println(count);//0
        thread1.join();
        thread2.join();
        System.out.println(count);

    }

    public static void main2(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //System.out.println("hello thread");
            while(true){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println(thread.getState());// new
        thread.start();
        System.out.println(thread.getState());// runnable
        Thread.sleep(1000);
        System.out.println(thread.getState());// terminated / timed_waiting
    }

    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("thread线程结束");
        });
        thread.start();

        //Thread.sleep(2000);
        thread.join(3000);//等待的最大时间
        System.out.println("main线程结束");
    }
}
