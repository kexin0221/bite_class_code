package network;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;

public class UDPDictServer extends UDPEchoServer{

    private final HashMap<String, String> dict = new HashMap<>();

    public UDPDictServer (int port) throws SocketException {
        super(port);

        // 初始化字典
        dict.put("小狗", "dog");
        dict.put("小猫", "cat");
        dict.put("小兔", "rabbit");
        dict.put("小鸭", "duck");
    }

    @Override
    public String process(String request) {
        // 查字典
        return dict.getOrDefault(request, "未找到该词条");
    }

    public static void main(String[] args) throws IOException {
        UDPDictServer udpDictServer = new UDPDictServer(9090);
        udpDictServer.start();
    }
}
