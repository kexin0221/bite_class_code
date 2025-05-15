package multithreading;

import java.util.Scanner;

public class test_3_13 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("ABC");
        }
    }

    public static void main4(String[] args) throws InterruptedException {
        Object locker1 = new Object();
        Object locker2 = new Object();
        Object locker3 = new Object();
        Thread t1 = new Thread(() -> {
            try{
                for (int i = 0; i < 10; i++) {
                    synchronized (locker1){
                        locker1.wait();
                    }
                    System.out.print('A');
                    synchronized (locker2){
                        locker2.notify();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try{
                for (int i = 0; i < 10; i++) {
                    synchronized (locker2){
                        locker2.wait();
                    }
                    System.out.print('B');
                    synchronized (locker3){
                        locker3.notify();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try{
                for (int i = 0; i < 10; i++) {
                    synchronized (locker3){
                        locker3.wait();
                    }
                    System.out.println('C');
                    synchronized (locker1){
                        locker1.notify();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        t3.start();

        //主线程中通知locker1
        Thread.sleep(1000);
        synchronized (locker1){
            locker1.notify();
        }
    }

    public static void main3(String[] args) {
        Object locker = new Object();
        Thread thread1 = new Thread(() -> {
            try{
                System.out.println("t1 wait之前");
                synchronized (locker){
                    locker.wait();
                }
                System.out.println("t1 wait之后");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try{
                System.out.println("t2 wait之前");
                synchronized (locker){
                    locker.wait();
                }
                System.out.println("t2 wait之后");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入任意内容，唤醒线程");
            scanner.next();
            synchronized (locker){
                locker.notifyAll();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main2(String[] args) {
        Object locker = new Object();
        Thread thread1 = new Thread(() -> {
            try{
                Thread.sleep(10000);
                System.out.println("wait之前");
                synchronized (locker){
                    locker.wait();
                }
                System.out.println("wait之后");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("请输入任何内容：");
                scanner.next();// 等待IO进入的阻塞
            }
            synchronized (locker) {
                locker.notify();
                // 同样是要先拿到锁，再进行notify
                // 在Java中强制要求notify搭配synchronized
            }
        });
        thread1.start();
        thread2.start();
        // 务必要先wait再notify,才有作用
    }

    public static void main1(String[] args) throws InterruptedException {
        Object object = new Object();
        System.out.println("wait之前");
        synchronized (object) {
            // 加锁的
            object.wait();// 解锁的
            // wait就是先释放Object对象对应的锁 (前提是：Object对象应该处于加锁状态，才能释放)
            // 加锁的
        }// 要求synchronized的锁对象必须和wait的对象是同一个
        System.out.println("wait之后");
    }
}
