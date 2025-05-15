package FileOperationandIO;

import java.io.*;
import java.util.Scanner;

public class Exercise_copyFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入源文件路径:");
        String srcRoot = scanner.next();
        System.out.println("请输入目标文件路径:");
        String destRoot = scanner.next();
        File srcFile = new File(srcRoot);
        if (!srcFile.isFile()) {
            System.out.println("源文件不存在！");
            return;
        }
        File destFile = new File(destRoot);
        //目标文件不一定存在,但是其根目录必须要求存在
        if (!destFile.getParentFile().isDirectory()) {
            System.out.println("目标文件的根目录不存在！");
            return;
        }

        // 复制文件
        try (InputStream inputStream = new FileInputStream(srcFile);
             OutputStream outputStream = new FileOutputStream(destFile)) {
            while (true) {
                byte[] bytes = new byte[1024];
                int data = inputStream.read();
                if (data == -1) {
                    break;
                }
                // 此处的write不应该写整个bytes数组
                // bytes数组不一定能被填满，要按照data这个长度来写入
                outputStream.write(bytes, 0, data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
