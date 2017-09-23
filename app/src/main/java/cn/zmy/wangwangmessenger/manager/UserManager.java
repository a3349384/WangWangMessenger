package cn.zmy.wangwangmessenger.manager;

import java.util.ArrayList;
import java.util.List;

import cn.zmy.wangwangmessenger.user.model.User;

/**
 * Created by zmy on 2017/9/20.
 */

public class UserManager
{
    private static UserManager instance;

    public static synchronized UserManager getInstance()
    {
        if (instance == null)
        {
            instance = new UserManager();
        }

        return instance;
    }

    private List<User> mUsers;

    public UserManager()
    {
        mUsers = new ArrayList<>();
    }

    public List<User> getUsers()
    {
        return mUsers;
    }
}
