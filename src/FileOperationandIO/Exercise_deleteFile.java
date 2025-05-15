package FileOperationandIO;

import java.io.File;
import java.util.Scanner;

public class Exercise_deleteFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要扫描的根目录：");
        String rootDirpath = scanner.next();
        File rootDir = new File(rootDirpath);
        if (!rootDir.isDirectory()) {
            System.out.println("输入的不是目录！");
            return;
        }
        System.out.println("请输入要删除的关键字：");
        String keyword = scanner.next();
        scanDir(rootDir, keyword);
    }

    private static void scanDir(File rootDir, String keyword) {
        // 1.列出当前目录中包含的所有文件
        File[] files = rootDir.listFiles();
        if (files == null) {
            return;
        }
        for (File file: files) {
            // 2.遍历files
            if (file.isFile()) {
                // 3.如果是文件，则判断是否含有关键字
                dealFile(file, keyword);
            } else {
                // 4.如果是目录，则递归调用该方法
                scanDir(file, keyword);
            }
        }
    }

    private static void dealFile(File file, String keyword) {
        if (file.getName().contains(keyword)) {
            System.out.println("发现文件：" + file.getAbsolutePath() + "\n是否删除(y/n)");
            Scanner scanner = new Scanner(System.in);
            String result = scanner.next();
            if (result.equals("y")) {
                file.delete();
                System.out.println("文件已删除！");
            }
        }
    }
}
