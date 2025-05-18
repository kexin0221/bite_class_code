package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPEchoServer {
    private ServerSocket serverSocket = null;

    public TCPEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            //先处理客户端发来的请求
            Socket clientSocket = serverSocket.accept();
            // processConnection(clientSocket);

            //多线程
//            Thread thread = new Thread(()->{
//                processConnection(clientSocket);
//            });
//            thread.start();

//            // 线程池
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    processConnection(clientSocket);
                }
            });
        }
    }

    private void processConnection(Socket clientSocket) {
        System.out.printf("[%s:%d] 客户端上线!\n", clientSocket.getInetAddress(), clientSocket.getPort());
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            Scanner scanner = new Scanner(inputStream);
            PrintWriter writer = new PrintWriter(outputStream);
            while (true) {
                // 1.读取客户端发来的请求
                if (!scanner.hasNext()) {
                    //连接断开了
                    System.out.printf("[%s:%d] 客户端下线!\n", clientSocket.getInetAddress(),
                            clientSocket.getPort());
                    break;
                }
                String request = scanner.next();
                // 2.根据请求计算响应
                String response = process(request);
                // 3.返回响应到客户端
                // outputStream.write(response.getBytes());
                writer.println(response);
                writer.flush();
                // 打印日志
                System.out.printf("[%s:%d] req:%s resp:%s\n", clientSocket.getInetAddress(),
                        clientSocket.getPort(), request, response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TCPEchoServer tcpEchoServer = new TCPEchoServer(9090);
        tcpEchoServer.start();
    }
}
