package multithreading;

import java.util.*;
import java.util.concurrent.*;

//实现一个固定线程个数的线程池
class myThreadPool{
    private BlockingQueue<Runnable> queue = null;
    public myThreadPool(int n){
        // 初始化线程池，创建固定个数线程的线程池
        // 这里使用ArrayBlockingQueue作为任务队列，容量为1000
        queue = new ArrayBlockingQueue<>(1000);

        // 创建n个线程
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    while(true) {
                        Runnable task = queue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }
    }

    public void submit(Runnable task) throws InterruptedException {
        // 将任务放在队列中
        queue.put(task);
    }
}

// 自己实现一个定时器
class myTimer {
    private final PriorityQueue<timerTask> queue = new PriorityQueue<>();

    private final Object locker = new Object();

    public void schedule(Runnable task, long delay){
        synchronized (locker) {
            // 若把这句放在锁外面，可能会因为锁阻塞，使得时间存在一定的误差
            timerTask timertask = new timerTask(task, System.currentTimeMillis() + delay);
            queue.offer(timertask);
            locker.notify();
        }
    }

    public myTimer(){
        // 创建一个线程，负责执行线程中的任务
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    synchronized (locker) {
                        // 取出队首元素
                        while (queue.isEmpty()) {
                            // 这里sleep的时间不好设置，一定不能用sleep
                            // Thread.sleep();
                            locker.wait();
                        }
                        timerTask task = queue.peek();
                        if (System.currentTimeMillis() < task.getTime()) {
                            // 如果任务时间比系统时间大，说明执行任务的时机未到
                            continue;
                        } else {
                            // 时机到了，执行任务
                            task.run();
                            queue.poll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
}

class timerTask implements Comparable<timerTask>{
    private final Runnable task;
    private final long time;

    public timerTask(Runnable task, long time) {
        this.task = task;
        this.time = time;
    }

    @Override
    public int compareTo(timerTask o){
        return (int)(this.time - o.time);
    }

    public long getTime() {
        return time;
    }

    public void run() {
        task.run();
    }
}

public class test_3_17 {
    public static void main(String[] args) {
        myTimer timer = new myTimer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 3000");
            }
        },3000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 2000");
            }
        },2000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 1000");
            }
        },1000);
        System.out.println("hello main");
    }

    public static void main2(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello 3000");
            }
        },3000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello 2000");
            }
        },2000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello 1000");
            }
        },1000);
        System.out.println("Hello main");
    }

    public static void main1(String[] args) throws InterruptedException {
        // myThreadPool threadPool = new myThreadPool(10);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //向线程池提交任务
        for (int i = 0; i < 100; i++) {
            int id = i;
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " id = " + id);
            });
        }
        threadPool.shutdown();
    }
}
