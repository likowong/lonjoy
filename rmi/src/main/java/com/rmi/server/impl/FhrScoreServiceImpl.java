package com.rmi.server.impl;

import com.rmi.server.FhrScoreService;
import com.rmi.utils.JNIUtilsNew;
import com.rmi.utils.ReturnMsg;
import com.rmi.vo.MonitorHeartrateRecord;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author luke
 * @date 2021/1/6 0006 13:55
 * @desc
 **/
public class FhrScoreServiceImpl extends UnicastRemoteObject implements FhrScoreService {

    public FhrScoreServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public ReturnMsg getKrebsFhrInfo(MonitorHeartrateRecord record, Integer start, Integer end) throws RemoteException {
        return JNIUtilsNew.getKrebsFhrInfo(record, start, end);
    }
}
