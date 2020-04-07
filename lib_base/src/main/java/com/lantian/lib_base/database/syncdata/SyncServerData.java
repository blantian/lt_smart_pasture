package com.lantian.lib_base.database.syncdata;

/**
 * Created by Sherlock·Holmes on 2020-03-26
 * 与服务器数据同步类
 */
public class SyncServerData {

    private final int ADD = SyncDataStatus.ADD.ordinal();
    private final int UPDATE = SyncDataStatus.UPDATE.ordinal();
    private final int DELETE = SyncDataStatus.DELETE.ordinal();
    private final int NONE = SyncDataStatus.NONE.ordinal();


}
