package cn.zmy.wangwangmessenger.user.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by zmy on 2017/9/20.
 */

@Entity
public class User
{
    public static final int STATUS_NONE = 0;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILED = 2;

    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    private int status;

    @Generated(hash = 788285001)
    public User(Long id, @NotNull String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public static User of(String name)
    {
        User user = new User();
        user.setName(name);

        return user;
    }

    public static final class UserStatusConverter
    {
        public static String getDescByStatus(int status)
        {
            switch (status)
            {
                case User.STATUS_NONE:
                {
                    return "等待发送";
                }
                case User.STATUS_SUCCESS:
                {
                    return "发送成功";
                }
                case User.STATUS_FAILED:
                {
                    return "发送失败";
                }
                default:
                {
                    return "状态未知";
                }
            }
        }
    }
}
