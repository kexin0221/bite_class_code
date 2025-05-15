package multithreading;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class test_3_21 {
    public static void main(String[] args) throws InterruptedException {
        // 现在把整个任务拆成10个部分，每个部分视为是一个“子任务”.
        // 可以把这10个子任务丢到线程池中，让线程池执行
        // 当然也可以安排10个独立的线程执行

        // 构造方法中传入的10代表任务的个数
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int id = i;
            executorService.submit(() -> {
                System.out.println("子任务开始执行：" + id);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("子任务结束执行：" + id);
                latch.countDown();
            });
        }
        // 这个方法阻塞等待所有的任务结束
        latch.await();
        System.out.println("所有任务执行完毕");
        executorService.shutdown();
    }

    public static void main5(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                try {
                    semaphore.acquire();
                    count++;
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                try {
                    semaphore.acquire();
                    count++;
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + count);
    }

    public static void main4(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        semaphore.acquire();
        System.out.println("进行一次P操作");
        semaphore.acquire();
        System.out.println("进行一次P操作");
        semaphore.acquire();
        System.out.println("进行一次P操作");
        semaphore.acquire();
        System.out.println("进行一次P操作");
    }

    private static int count = 0;
    public static void main3(String[] args) throws InterruptedException {
        ReentrantLock locker = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                locker.lock();
                count++;
                locker.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                locker.lock();
                count++;
                locker.unlock();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + count);
    }

    private static int total = 0;
    public static void main2(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int sum = 0;
            for (int i = 0; i <= 100; i++) {
                sum += i;
            }
            total = sum;
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        System.out.println(total);
    }

    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        // 此时callable知识定义了一个带有返回值的任务，并没有执行
        // 执行还是需要搭配Thread对象
        Callable<Integer> callable = () -> {
            int result = 0;
            for (int i = 1; i <= 100; i++) {
                result += i;
            }
            return result;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}
