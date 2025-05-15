package FileOperationandIO;

import java.io.File;
import java.io.IOException;

public class test_4_10 {
    public static void main(String[] args) throws IOException {
        //File file = new File("C:/Program Files/Java/jdk-17");
        File file = new File("./test.txt");
        System.out.println(file.getParent());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath()); //绝对路径的简化版本

        file.createNewFile();

        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());

    }

    public static void main1(String[] args) {
        String path = "./test_4_10";
        //基准路径是谁？
        //1.在IDEA中直接运行
        //基准路径就是项目的目录(D:\code\java\bite_class_code)
        //2.打一个jar包，单独运行jar包
        //当前在哪个目录下执行运行命令，基准目录就是哪个目录
        //3.打成一个war包，放到tomcat中去执行
        //基准路径就是tomcat的bin目录
    }
}
