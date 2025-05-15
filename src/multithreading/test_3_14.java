package multithreading;

//// 饿汉模式
//class Singleton{
//    private static Singleton instance = new Singleton();
//
//    public static Singleton getInstance() {
//        return instance;
//    }
//
//    private Singleton() {
//
//    }
//}

// 懒汉模式(推荐)
class SingletonLazy{
    private static SingletonLazy instance = null;
    public static SingletonLazy getInstance() {
        if(instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
    private SingletonLazy() {}
}

public class test_3_14 {
    public static void main(String[] args) {
        SingletonLazy s1 = SingletonLazy.getInstance();
        SingletonLazy s2 = SingletonLazy.getInstance();
        System.out.println(s1 == s2);
    }

//    public static void main1(String[] args) {
//        Singleton t1 = Singleton.getInstance();
//        Singleton t2 = Singleton.getInstance();
//        System.out.println(t1 == t2);
//    }
}
