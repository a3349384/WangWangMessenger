package cn.zmy.wangwangmessenger.manager;

import java.net.HttpCookie;
import java.util.List;

import cn.zmy.common.utils.CollectionUtil;

/**
 * Created by zmy on 2017/9/20.
 */

public class CookieManager
{
    private static CookieManager instance;

    public static synchronized CookieManager getInstance()
    {
        if (instance == null)
        {
            instance = new CookieManager();
        }

        return instance;
    }

    private String h5tk;
    private List<HttpCookie> httpCookies;

    public String getH5tk()
    {
        return h5tk;
    }

    public void setH5tk(String h5tk)
    {
        this.h5tk = h5tk;
    }

    public List<HttpCookie> getHttpCookies()
    {
        return httpCookies;
    }

    public void setHttpCookies(List<HttpCookie> httpCookies)
    {
        this.httpCookies = httpCookies;
    }

    public void changeCookie(HttpCookie httpCookie)
    {
        if (httpCookie == null)
        {
            return;
        }

        HttpCookie hc = CollectionUtil.firstOrDefault(httpCookies, item -> item.getName().contentEquals(httpCookie.getName()));
        if (hc != null)
        {
            hc.setValue(httpCookie.getValue());
        }
    }
}
