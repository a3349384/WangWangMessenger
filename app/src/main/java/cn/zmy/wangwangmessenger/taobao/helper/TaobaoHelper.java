package cn.zmy.wangwangmessenger.taobao.helper;

import java.net.HttpCookie;
import java.util.List;

import cn.zmy.common.utils.CollectionUtil;
import cn.zmy.common.utils.EncryptUtil;
import cn.zmy.wangwangmessenger.base.helper.CookieHelper;

/**
 * Created by zmy on 2017/9/20.
 */

public class TaobaoHelper
{
    public static String getH5tk()
    {
        List<HttpCookie> httpCookies = CookieHelper.stringToHttpCookies(CookieHelper.getTaobaoWebCookiesString());
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
