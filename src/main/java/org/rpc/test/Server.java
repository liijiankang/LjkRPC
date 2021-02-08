package org.rpc.test;

import org.rpc.impl.HelloWorldImpl;
import org.rpc.server.IOService;
import org.rpc.server.RpcExploreService;

import java.io.IOException;

/**
 * @author jiankang Li
 * @ClassName Server.java
 * @Description TODO
 * @createTime 2021年02月08日 11:14:00
 */
public class Server {
    public static void main(String[] args) {
        RpcExploreService rpcExploreService = new RpcExploreService();
        // 传入的字符串是接口的全名称
        rpcExploreService.explore("new2019.rpc.rpc_v1.expore.Helloworld", new HelloWorldImpl());

        try {
            Runnable ioService = new IOService(rpcExploreService, 10001);
            new Thread(ioService).start();
            // 开启了端口为10001的服务监听
        } catch (IOException e) {
        }
    }
}
