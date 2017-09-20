package cn.zmy.wangwangmessenger.helper;

import android.text.TextUtils;
import android.webkit.CookieManager;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zmy on 2017/9/20.
 */

public class CookieHelper
{
    public static String getCookiesString()
    {
        String cookiesString = CookieManager.getInstance().getCookie("api.m.taobao.com");
        return cookiesString;
    }

    public static List<HttpCookie> parseCookieString()
    {
        List<HttpCookie> httpCookies = new ArrayList<>();
        String cookiesString = getCookiesString();
        if (TextUtils.isEmpty(cookiesString))
        {
            return httpCookies;
        }
        String[] cookiesStringArr = cookiesString.split(";");
        for (String cookieString : cookiesStringArr)
        {
            cookiesString = cookiesString.trim();
            String[] tmp = cookieString.split("=");
            String name = tmp[0];
            String value = tmp[1].substring(0, tmp[1].length() -1 );
            HttpCookie httpCookie = new HttpCookie(name, value);
            httpCookies.add(httpCookie);
        }
        return httpCookies;
    }
}
