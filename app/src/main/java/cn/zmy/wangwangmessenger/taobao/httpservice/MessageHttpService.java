package cn.zmy.wangwangmessenger.taobao.httpservice;

import java.net.HttpCookie;
import java.util.List;

import cn.zmy.common.json.JsonUtil;
import cn.zmy.wangwangmessenger.base.helper.CookieHelper;
import cn.zmy.wangwangmessenger.taobao.helper.TaobaoHelper;
import cn.zmy.wangwangmessenger.manager.CookieManager;
import cn.zmy.wangwangmessenger.manager.HttpManager;
import cn.zmy.wangwangmessenger.taobao.model.MessageData;
import cn.zmy.wangwangmessenger.taobao.model.MessageSendResponse;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zmy on 2017/9/20.
 */

public class MessageHttpService
{
    private static final String URL_SEND_MESSAGE_BASE = "https://api.m.taobao.com/rest/h5ApiUpdate.do?callback=jsonp30&type=jsonp&api=mtop.ww.sendMessage&v=1.0&";
    private static final String APPKEY = "12574478";

    public static MessageSendResponse sendMessage(String userName, String message)
    {
        try
        {
            String data = JsonUtil.toString(MessageData.of(userName, message));
            long time = System.currentTimeMillis();
            String sign = TaobaoHelper.sign(CookieManager.getInstance().getH5tk(), APPKEY, data, time);
            String url = URL_SEND_MESSAGE_BASE + String.format("data=%s&appKey=12574478&sign=%s&t=%d", data, sign, time);

            Request request = new Request.Builder()
                                      .url(url)
                                      .header("Accept", "*/*")
                                      .header("User-Agent", System.getProperty("http.agent"))
                                      .header("Referer", "https://h5.m.taobao.com/ww/index.htm")
                                      .header("Accept-Encoding", "gzip, deflate")
                                      .header("Accept-Language", "zh-CN,en-US;q=0.8")
                                      .header("Cookie", CookieHelper.httpCookiesToString(CookieManager.getInstance().getHttpCookies()))
                                      .build();
            Response response = HttpManager.getInstance().getHttpClient().newCall(request).execute();
            if (response.code() != 200)
            {
                return null;
            }
            List<String> cookies = response.headers("Set-Cookie");
            if (cookies != null && cookies.size() > 0)
            {
                for (String cookie : cookies)
                {
                    List<HttpCookie> httpCookies = HttpCookie.parse(cookie);
                    if (httpCookies != null)
                    {
                        for (HttpCookie httpCookie : httpCookies)
                        {
                            CookieManager.getInstance().changeCookie(httpCookie);
                        }
                    }
                }
            }
            String responseString = response.body().string();
            int first = responseString.indexOf('{');
            int last = responseString.lastIndexOf('}');
            return JsonUtil.fromString(responseString.substring(first, last + 1), MessageSendResponse.class);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
