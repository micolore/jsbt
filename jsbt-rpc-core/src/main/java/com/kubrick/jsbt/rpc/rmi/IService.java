package com.kubrick.jsbt.rpc.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author k
 * @version 1.0.0
 * @ClassName IService
 * @description: TODO
 * @date 2021/2/21 下午11:53
 */
public interface IService extends Remote {

    public String queryName(String no) throws RemoteException;

}
