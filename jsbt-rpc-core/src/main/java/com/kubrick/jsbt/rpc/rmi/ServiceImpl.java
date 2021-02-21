package com.kubrick.jsbt.rpc.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ServiceImpl
 * @description: TODO
 * @date 2021/2/21 下午11:54
 */
public class ServiceImpl extends UnicastRemoteObject implements IService {

    private static final long serialVersionUID = 682805210518738166L;

    /**
     * @throws RemoteException
     */
    public ServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String queryName(String no) throws RemoteException {
        // 方法的具体实现
        System.out.println("hello " + no);
        return String.valueOf(System.currentTimeMillis());
    }
}
