package com.kira.extra;

import android.os.StrictMode;

/**
 * Created by kira on 09/05/15.
 * android2.3以上网络通信需要异步操作
 * 使用该类能让主程序使用网络服务
 */
public class EnableWebService {

    public static void enableWebService(){

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
