package cn.zmy.wangwangmessenger.manager;

import android.content.Context;

/**
 * Created by zmy on 2017/9/23.
 */

public class ContextManager
{
    private static ContextManager instance;

    public static synchronized ContextManager getInstance()
    {
        if (instance == null)
        {
            instance = new ContextManager();
        }

        return instance;
    }

    private Context mAppContext;

    private ContextManager(){}

    public Context getAppContext()
    {
        return mAppContext;
    }

    public void setAppContext(Context mAppContext)
    {
        this.mAppContext = mAppContext;
    }
}
