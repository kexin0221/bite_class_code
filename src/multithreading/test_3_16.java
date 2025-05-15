package multithreading;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//平面上的一个点
class Point{
    //工厂模式
    public static Point makePointByXY(double x, double y){
        return new Point();
    }

    public static Point makePointByRA(double r, double a){
        return new Point();
    }
    //重载(参数个数或类型不同)
}
public class test_3_16 {
    public static void main(String[] args) {
        // ExecutorService threadPool = Executors.newFixedThreadPool(4);
        ExecutorService threadPool = Executors.newCachedThreadPool();
//        Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            int id = i;
            threadPool.submit(() -> {
                System.out.println("hello " + id + " " + Thread.currentThread().getName());
            });
        }
    }

    public static void main1(String[] args) {
        Thread producer = new Thread(() -> {
            while (true) {
                System.out.println("hello Thread");
            }
        });
        producer.start();
        while (true) {
            System.out.println("hello main");
        }
    }
}
