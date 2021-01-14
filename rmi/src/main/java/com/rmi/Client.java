package com.rmi;

import com.rmi.server.FhrScoreService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author luke
 * @date 2021/1/6 0006 13:58
 * @desc
 **/
public class Client {
    static FhrScoreService fhrScoreService;
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        //获取远程对象
        if(fhrScoreService == null){
            fhrScoreService = (FhrScoreService) Naming.lookup("//192.168.2.196:5000/fhrScoreService");
        }
        System.out.println(fhrScoreService.getKrebsFhrInfo(null,null,null));
    }
}
