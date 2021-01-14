package com.rmi.server;

import com.rmi.utils.ReturnMsg;
import com.rmi.vo.MonitorHeartrateRecord;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author luke
 * @date 2021/1/6 0006 13:52
 * @desc
 **/
public interface FhrScoreService extends Remote {
    /**
     * @return {@link null}
     * @author luke
     * @date 18:08 2021/1/6 0006
     * @desc 评分接口
     */
    ReturnMsg getKrebsFhrInfo(MonitorHeartrateRecord record, Integer start, Integer end) throws RemoteException;
}
