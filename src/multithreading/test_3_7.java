package multithreading;

public class test_3_7 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);

                    //1.加上break-->立即终止线程
                    //2.什么都不写-->不终止
                    //3.catch中先执行一些其他逻辑在break-->稍后终止
                    break;
                }
            }
            System.out.println("t结束");
        });
        thread.start();

        Thread.sleep(3000);
        thread.interrupt();// 这个操作导致InterruptedException异常，用break结束
        System.out.println("main线程尝试终止thread线程...");
    }

    public static void main4(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("tread:"+Thread.currentThread().getName());
        });
        thread.start();
        System.out.println("main:"+ Thread.currentThread().getName());
    }

    private static boolean isFinished = false;

    public static void main3(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while(!isFinished) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("thread 结束");
        });
        thread.start();
        Thread.sleep(3000);
        isFinished = true;
    }

    public static void main2(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

        while(true){
            System.out.println(thread.isAlive());
            Thread.sleep(1100);
        }
    }

    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while(true){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //后台线程的设置得在start之前进行
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 3; i++) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
        System.out.println("main 结束");
    }
}
