package FileOperationandIO;

import java.io.*;

public class test_5_10 {
    public static void main(String[] args) throws IOException {
        try (Writer writer = new FileWriter("./text.txt")) {
            writer.write("Hello World!");
        }
    }

    public static void main3(String[] args) throws IOException {
        // 读二进制文件
        try (InputStream inputStream = new FileInputStream("picture1.jpg")) {
            while (true) {
                int b = inputStream.read();
                if (b == -1) {
                    break;
                }
                System.out.printf("0x%x\n", b);
            }
        }
    }

    public static void main2(String[] args) {
        try (Reader reader = new FileReader("./text.txt")) {
            while (true) {
                int c = reader.read();
                if (c == -1) {
                    break;
                }
                System.out.println((char)c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main1(String[]args){
        try (OutputStream outputStream = new FileOutputStream("./output.txt", true)) {
            // 97在ascii码中对应'a'
//            outputStream.write(97);
//            outputStream.write(98);
//            outputStream.write(99);
            byte[] bytes = {99};
            outputStream.write(bytes);
        } catch (IOException e) {
            // 此处需要处理两个异常，但因为针对两个异常的处理方法是一样的，所以直接合并了
            throw new RuntimeException(e);
        }
    }

}
