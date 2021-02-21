package com.kubrick.jsbt.rpc.rmi.test;

import com.kubrick.jsbt.rpc.rmi.ServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author k
 * @version 1.0.0
 * @ClassName Server
 * @description: TODO
 * @date 2021/2/21 下午11:55
 */
public class Server {
    public static void main(String[] args) {
        // 注册管理器
        Registry registry = null;
        try {
            // 创建一个服务注册管理器
            registry = LocateRegistry.createRegistry(8088);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            // 创建一个服务
            ServiceImpl server = new ServiceImpl();
            // 将服务绑定命名
            registry.rebind("vince", server);

            System.out.println("bind server");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
