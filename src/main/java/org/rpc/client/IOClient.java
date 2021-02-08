package org.rpc.client;

import org.rpc.common.MethodParameter;

import java.io.*;
import java.net.Socket;

/**
 * @author jiankang Li
 * @ClassName IOClient.java
 * @Description 客户端 - 网络处理
 * @createTime 2021年02月08日 11:06:00
 */
public class IOClient {
    private String ip;
    private int port;
    public IOClient(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
    }

    public Object invoke(MethodParameter methodParameter) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream ouput = new ObjectOutputStream(outputStream);

            ouput.writeUTF(methodParameter.getClassName());
            ouput.writeUTF(methodParameter.getMethodName());
            ouput.writeObject(methodParameter.getParameterTypes());
            ouput.writeObject(methodParameter.getArguments());

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream input = new ObjectInputStream(inputStream);
            return input.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }
}
