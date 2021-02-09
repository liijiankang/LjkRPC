package org.rpc.test;

import org.rpc.client.IOClient;
import org.rpc.client.RpcUsedService;
import org.rpc.impl.Helloworld;

import java.io.IOException;
import java.util.Random;

/**
 * @author jiankang Li
 * @ClassName Client.java
 * @Description TODO
 * @createTime 2021年02月08日 11:14:00
 */
public class Client {
    public static void main(String[] args) {
        RpcUsedService rpcUsedService = new RpcUsedService();
        rpcUsedService.register(Helloworld.class);
        try {
            IOClient ioClient = new IOClient("127.0.0.1", 10001);
            // 网络套接字链接 同上是10001端口
            rpcUsedService.setIoClient(ioClient);

            Helloworld helloworld = rpcUsedService.get(Helloworld.class);
            // 生成的本地代理对象 proxy
            for(int i=0; i< 1; i++) {
                // 开启了100个线程
                new Thread(() -> {
                    long start = System.currentTimeMillis();
                    int a = new Random().nextInt(100);
                    int b = new Random().nextInt(100);
                    int c = helloworld.add(a, b);
                    System.out.println("a: " + a + ", b:" + b + ", c=" + c + ", 耗时:" + (System.currentTimeMillis() - start));
                }).start();
            }
        } catch (IOException e) {
        }
    }
}
