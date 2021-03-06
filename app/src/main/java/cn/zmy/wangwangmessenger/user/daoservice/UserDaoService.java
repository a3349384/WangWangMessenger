package cn.zmy.wangwangmessenger.user.daoservice;

import android.text.TextUtils;

import java.util.List;

import cn.zmy.common.utils.CollectionUtil;
import cn.zmy.common.utils.ViewUtil;
import cn.zmy.wangwangmessenger.manager.DaoManager;
import cn.zmy.wangwangmessenger.user.model.User;
import cn.zmy.wangwangmessenger.user.model.UserDao;

/**
 * Created by zmy on 2017/9/23.
 */

public class UserDaoService
{
    public void insert(String name)
    {
        if (TextUtils.isEmpty(name))
        {
            return;
        }
        User user = User.of(name);
        UserDao userDao = DaoManager.getInstance().getWritableDaoSession().getUserDao();
        userDao.insertInTx(user);
    }

    public void insert(List<User> users)
    {
        if (users == null)
        {
            return;
        }

        UserDao userDao = DaoManager.getInstance().getWritableDaoSession().getUserDao();
        userDao.insertInTx(users);
    }

    public void update(User user)
    {
        if (user == null || user.getId() == null)
        {
            return;
        }
        UserDao userDao = DaoManager.getInstance().getWritableDaoSession().getUserDao();
        userDao.updateInTx(user);
    }

    public List<User> list()
    {
        UserDao userDao = DaoManager.getInstance().getReadableDaoSession().getUserDao();
        return userDao.queryBuilder().list();
    }

    /**
     * 获取状态为指定status的用户
     * */
    public List<User> list(int status)
    {
        UserDao userDao = DaoManager.getInstance().getReadableDaoSession().getUserDao();
        return userDao.queryBuilder().where(UserDao.Properties.Status.eq(status)).list();
    }
}
