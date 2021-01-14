package com.rmi;

import com.rmi.server.FhrScoreService;
import com.rmi.server.impl.FhrScoreServiceImpl;
import com.rmi.utils.ConfigProUtils;
import com.rmi.utils.JNIUtilsNew;
import com.sun.jna.Native;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author luke
 * @date 2021/1/6 0006 13:52
 * @desc
 **/
@SpringBootApplication
public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        ConfigProUtils.init();
        JNIUtilsNew.JniDllNew instance = JNIUtilsNew.JniDllNew.Instance;
        System.out.println(instance);
        FhrScoreService fhrScoreService = new FhrScoreServiceImpl();
        LocateRegistry.createRegistry(5000);
        Naming.bind("//192.168.2.196:5000/fhrScoreService", fhrScoreService);
    }
}
