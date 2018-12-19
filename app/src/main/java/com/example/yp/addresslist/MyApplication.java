package com.example.yp.addresslist;

import android.app.Application;

public class MyApplication extends Application {
    private UtilDAO dao;

    /**
     * 创建时调用
     * */
    @Override
    public void onCreate() {
        super.onCreate();
        dao = new UtilDAO(this);
    }

    /**
     * 后台进程终止，前台程序需要内存时调用此方法，用于释放内存
     * 用于关闭数据库连接
     * */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        dao.getClose();
    }

    public UtilDAO getDao() {
        return dao;
    }
}
