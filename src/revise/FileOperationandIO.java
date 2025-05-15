package revise;

import java.io.*;

public class FileOperationandIO {
    public static void main(String[] args) throws IOException {
        try (Writer writer = new FileWriter("./text.txt")) {
            writer.write("Hello World!");
        }
    }
}
