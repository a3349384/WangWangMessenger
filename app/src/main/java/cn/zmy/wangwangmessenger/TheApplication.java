package cn.zmy.wangwangmessenger;

import android.app.Application;

import cn.zmy.wangwangmessenger.manager.ContextManager;

/**
 * Created by zmy on 2017/9/6.
 */

public class TheApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        ContextManager.getInstance().setAppContext(this);
    }
}
