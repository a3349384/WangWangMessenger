package cn.zmy.wangwangmessenger.manager;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by zmy on 2017/9/20.
 */

public class HttpManager
{
    private static HttpManager instance;

    public static synchronized HttpManager getInstance()
    {
        if (instance == null)
        {
            instance = new HttpManager();
        }

        return instance;
    }

    private OkHttpClient mHttpClient;

    public HttpManager()
    {
        mHttpClient = new OkHttpClient.Builder()
                              .readTimeout(10, TimeUnit.SECONDS)
                              .writeTimeout(10, TimeUnit.SECONDS)
                              .connectTimeout(10, TimeUnit.SECONDS)
                              .build();
    }

    public OkHttpClient getHttpClient()
    {
        return mHttpClient;
    }
}
