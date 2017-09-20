package cn.zmy.wangwangmessenger.manager;

/**
 * Created by zmy on 2017/9/20.
 */

public class TaobaoManager
{
    private static TaobaoManager instance;

    public static synchronized TaobaoManager getInstance()
    {
        if (instance == null)
        {
            instance = new TaobaoManager();
        }

        return instance;
    }

    private String h5tk;

    public String getH5tk()
    {
        return h5tk;
    }

    public void setH5tk(String h5tk)
    {
        this.h5tk = h5tk;
    }
}
