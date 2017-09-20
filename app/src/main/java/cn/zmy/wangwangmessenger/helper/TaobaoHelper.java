package cn.zmy.wangwangmessenger.helper;

import android.webkit.CookieManager;

import java.net.HttpCookie;
import java.util.List;

import cn.zmy.common.utils.CollectionUtil;
import cn.zmy.common.utils.EncryptUtil;

/**
 * Created by zmy on 2017/9/20.
 */

public class TaobaoHelper
{
    public static String getH5tk()
    {

        List<HttpCookie> httpCookies = CookieHelper.parseCookieString();
        HttpCookie httpCookie = CollectionUtil.firstOrDefault(httpCookies, HttpCookie -> HttpCookie.getName().contentEquals("_m_h5_tk"));
        if (httpCookie == null)
        {
            return null;
        }
        return httpCookie.getValue().split("_")[0];
    }

    public static String sign(String h5tk, String appKey, String data, long time)
    {
        String s = h5tk + "&" + time + "&" + appKey + "&" + data;
        return EncryptUtil.toMd5(s).toLowerCase();
    }
}
