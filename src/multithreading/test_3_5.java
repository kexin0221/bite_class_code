package multithreading;

import java.lang.Thread; //java.lang默认import,这句可以省略

class MyThread extends Thread{
    //run 相当于线程的入口程序
    @Override
    public void run() {
        while(true){
            System.out.println("hello thread");
            try {
                Thread.sleep(1000);//sleep是一个静态方法
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("hello thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class test_3_5 {
    //main方法对应的线程就是主线程
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }

    }

    public static void main4(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("hello thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();
        while(true){
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }

    public static void main3(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                while(true) {
                    System.out.println("hello thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t.start();
        while(true){
            System.out.println("hello main");
            Thread.sleep(100);
        }
    }

    public static void main2(String[] args) throws InterruptedException {
        Runnable runnable = new MyRunnable();
        Thread t = new Thread(runnable);
        t.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }

    public static void main1(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        //真正在系统中创建出一个线程
        t.start();
        //run没有创建线程,只是直接调用刚才重写的run
        //此时整个进程中只有一个main线程
        //t.run();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}