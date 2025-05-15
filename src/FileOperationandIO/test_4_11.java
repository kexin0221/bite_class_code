package FileOperationandIO;

import java.io.File;

public class test_4_11 {
    public static void main(String[] args) {
        File file = new File("./test");
        File newfile = new File("./test2");
        System.out.println(file.renameTo(newfile));
    }

    public static void main3(String[] args) {
        File file = new File("./test/111/222");
        Boolean result = file.mkdirs();
        System.out.println(result);
    }

    public static void main2(String[] args) {
        File file = new File("./test");
        Boolean result = file.mkdir();
        System.out.println(result);
        file.delete();
    }

    public static void main1(String[] args) throws InterruptedException {
        File file = new File("./test.txt");
//        Boolean result = file.delete();
//        System.out.println(result);
        // 在进程结束时退出文件
        file.deleteOnExit();
        Thread.sleep(10000);
    }
}
