package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPEchoClient {

    private Socket socket = null;

    public TCPEchoClient(String serverIP, int serverPort) throws IOException {
        socket = new Socket(serverIP, serverPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            Scanner scannerNet = new Scanner(inputStream);
            PrintWriter writer = new PrintWriter(outputStream);

            while (true) {
                // 1.从控制台读取输入
                String request = scanner.next();
                // 2.发送给服务器
                writer.println(request);
                writer.flush();
                // 3.读取服务器返回的响应
                String response = scannerNet.next();
                // 4.输出响应
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TCPEchoClient tcpEchoClient = new TCPEchoClient("127.0.0.1", 9090);
        tcpEchoClient.start();
    }
}
