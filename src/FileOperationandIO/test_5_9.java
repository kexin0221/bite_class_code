package FileOperationandIO;

import java.io.*;
import java.util.Arrays;

public class test_5_9 {
    public static void main(String[] args) throws IOException {
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream("./text.txt");
//            // 相当于C语言中的fopen
//        } finally {
//            inputStream.close(); // 关闭文件
//        }
        try (InputStream inputStream = new FileInputStream("./text.txt")) {
            //读文件操作
            while (true) {
                // 一次读一个字节
//                int data = inputStream.read();
//                if (data == -1) {
//                    // 文件读完
//                    break;
//                }
//                //System.out.println(data);
//                System.out.printf("0x%x\n", data); //打印utf-8的十六进制编码

                // 一次读多个字节
                byte[] data = new byte[3];
                // 读操作，就会尽可能的把字节数组给填满
                // 填不满的话能填几个就是几个
                // 此处的n就表示实际读了几个字节
                int n = inputStream.read(data);
                System.out.println("n = " + n);
                if (n == -1) {
                    break;
                }
                for (int i = 0; i < n; i++) {
                    System.out.printf("0x%x\n", data[i]);
                }
                System.out.println("==================");
            }
        }
    }

    public static void main5(String[] args) {
        File file = new File("./text");
        File newName = new File("./text2");
        boolean result = file.renameTo(newName);
        System.out.println(result);
    }

    public static void main4(String[] args) {
        File file = new File("./text/111/222");
        boolean result = file.mkdirs();
        System.out.println(result);
    }

    public static void main3(String[] args) {
        //File file = new File("./text.txt");
        File file = new File("C:/");
        String[] list = file.list();
        System.out.println(Arrays.toString(list));

        File[] files = file.listFiles();
        System.out.println(Arrays.toString(files));
    }

    public static void main2(String[] args) throws InterruptedException {
        File file = new File("./text.txt");
        file.deleteOnExit();
        Thread.sleep(10000);
    }

    public static void main1(String[] args) {
        File file = new File("./text.txt");
        boolean result = file.delete();
        System.out.println(result);
    }
}
