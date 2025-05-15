package multithreading;

import java.util.concurrent.*;

class myBlockingDeque{
    private String[] data = null;

    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public myBlockingDeque(int capacity){
        data = new String[capacity];
    }

    public void put(String elem) throws InterruptedException {
        synchronized (this) {
            if (size >= data.length) {
                // return;
                this.wait(); //队列不满的时候才要唤醒
            }
            data[tail] = elem;
            tail++;
            if (tail >= data.length) {
                tail = 0;
            }
            //tail = (tail + 1) % data.length;
            size++;
            this.notify();
        }
    }

    public String take() throws InterruptedException {
        synchronized (this) {
            if (size == 0) {
                // return null;
                this.wait(); //队列不空的时候才要唤醒
            }
            String ret = data[head];
            head++;
            if (head >= data.length) {
                head = 0;
            }
            size--;
            this.notify();
            return ret;
        }
    }
}

public class test_3_15 {
    public static void main(String[] args) {
        myBlockingDeque queue = new myBlockingDeque(1000);
        Thread producer = new Thread(() -> {
            int n = 0;
            while(true){
                try {
                    queue.put(n+"");
                    System.out.println("生产元素" + n);
                    n++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"producer");

        Thread consumer = new Thread(() -> {
            while (true){
                try {
                    String n = queue.take();
                    System.out.println("消费元素" + n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"consumer");
        producer.start();
        consumer.start();
    }

    public static void main2(String[] args) {
        BlockingDeque<Integer> queue = new LinkedBlockingDeque<>(100);

        Thread producer = new Thread(() -> {
            int n = 0;
            while(true){
                try {
                    queue.put(n);
                    System.out.println("生产元素" + n);
                    n++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"producer");

        Thread consumer = new Thread(() -> {
            while (true){
                try {
                    Integer n = queue.take();
                    System.out.println("消费元素" + n);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"consumer");
        producer.start();
        consumer.start();
    }

    public static void main1(String[] args) throws InterruptedException {
        BlockingDeque<String> queue = new LinkedBlockingDeque<>(100);
//        queue.put("aaa");
//        String elem = queue.take(); // put和take才带有阻塞功能，offer和poll没有
//        System.out.println(elem);
        for (int i = 0; i < 100; i++) {
            queue.put("aaa");
        }
        System.out.println("队列已满");
        queue.put("aaa");
        System.out.println("队列尝试put元素");
    }
}
