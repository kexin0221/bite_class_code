package FileOperationandIO;

import java.io.*;
import java.util.Scanner;

// 扫描指定目录， 并找到名称或者内容中包含制定字符的所有普通文件
public class Exercise_seekFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要扫描的目录：");
        String rootPath = scanner.next();
        File rootFile = new File(rootPath);
        if (!rootFile.isDirectory()) {
            System.out.println("输入的不是目录！");
            return;
        }
        System.out.println("请输入要搜索的关键词：");
        String keyword = scanner.next();

        scanDir(rootFile, keyword);
    }

    private static void scanDir(File rootFile, String keyword) {
        File[] files = rootFile.listFiles();
        if (files == null) {
            return;
        }
        for (File file: files) {
            if (file.isFile()) {
                seekFile(file, keyword);
            } else {
                scanDir(file, keyword);
            }
        }
    }

    private static void seekFile(File file, String keyword) {
        if (file.getName().contains(keyword)) {
            System.out.println("找到名称中含Keyword的文件：" + file.getAbsolutePath());
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (Reader reader = new FileReader(file)) {
            while (true) {
                char[] chars = new char[1024];
                int data = reader.read(chars);
                if (data == -1) {
                    break;
                }
                stringBuilder.append(chars, 0, data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (stringBuilder.indexOf(keyword) >= 0) {
            System.out.println("找到内容中包含keyword的文件：" + file.getAbsolutePath());
        }
    }
}
