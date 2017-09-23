package cn.zmy.wangwangmessenger.base.helper;

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
    public static String getTaobaoWebCookiesString()
    {
        String cookiesString = CookieManager.getInstance().getCookie("api.m.taobao.com");
        return cookiesString;
    }

    public static List<HttpCookie> stringToHttpCookies(String cookiesString)
    {
        if (TextUtils.isEmpty(cookiesString))
        {
            return null;
        }
        List<HttpCookie> httpCookies = new ArrayList<>();
        if (TextUtils.isEmpty(cookiesString))
        {
            return httpCookies;
        }
        String[] cookiesStringArr = cookiesString.split(";");
        for (String cookieString : cookiesStringArr)
        {
            cookieString = cookieString.trim();
            int first = cookieString.indexOf('=');
            String name = cookieString.substring(0, first);
            String value = cookieString.substring(first + 1);
            HttpCookie httpCookie = new HttpCookie(name, value);
            httpCookies.add(httpCookie);
        }
        return httpCookies;
    }

    public static String httpCookiesToString(List<HttpCookie> httpCookies)
    {
        if (httpCookies == null)
        {
            return null;
        }

        StringBuilder cookieStringBuilder = new StringBuilder();
        for (HttpCookie httpCookie : httpCookies)
        {
            if (cookieStringBuilder.length() > 0)
            {
                cookieStringBuilder.append("; ");
            }
            cookieStringBuilder.append(httpCookie.getName());
            cookieStringBuilder.append("=");
            cookieStringBuilder.append(httpCookie.getValue());
        }
        return cookieStringBuilder.toString();
    }
}

