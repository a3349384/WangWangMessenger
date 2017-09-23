package cn.zmy.wangwangmessenger.taobao.model;

import java.util.List;

/**
 * Created by zmy on 2017/9/20.
 */

public class MessageSendResponse
{
    private String api;
    private String v;
    private List<String> ret;

    public String getApi()
    {
        return api;
    }

    public void setApi(String api)
    {
        this.api = api;
    }

    public String getV()
    {
        return v;
    }

    public void setV(String v)
    {
        this.v = v;
    }

    public List<String> getRet()
    {
        return ret;
    }

    public void setRet(List<String> ret)
    {
        this.ret = ret;
    }

    public boolean isSuccess()
    {
        if (ret == null || ret.size() == 0)
        {
            return false;
        }

        return ret.get(0).contains("SUCCESS");
    }
}
