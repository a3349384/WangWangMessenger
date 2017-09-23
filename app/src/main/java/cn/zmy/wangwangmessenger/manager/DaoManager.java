package cn.zmy.wangwangmessenger.manager;

import cn.zmy.wangwangmessenger.user.model.DaoMaster;
import cn.zmy.wangwangmessenger.user.model.DaoSession;

/**
 * Created by zmy on 2017/9/23.
 */

public class DaoManager
{
    private static DaoManager instance;

    public static synchronized DaoManager getInstance()
    {
        if (instance == null)
        {
            instance = new DaoManager();
        }

        return instance;
    }

    private DaoSession mReadableDaoSession;
    private DaoSession mWritableDaoSession;

    private DaoManager()
    {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ContextManager.getInstance().getAppContext(), "ww");
        mReadableDaoSession = new DaoMaster(helper.getReadableDb()).newSession();
        mWritableDaoSession = new DaoMaster(helper.getWritableDb()).newSession();
    }

    public DaoSession getReadableDaoSession()
    {
        return mReadableDaoSession;
    }

    public DaoSession getWritableDaoSession()
    {
        return mWritableDaoSession;
    }
}
