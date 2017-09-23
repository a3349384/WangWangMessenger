package cn.zmy.wangwangmessenger.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.zmy.wangwangmessenger.base.Launcher;
import cn.zmy.wangwangmessenger.R;

public class LoginActivity extends AppCompatActivity
{
    private WebView mWebView;
    private ScanCookieHandler mScanCookieHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("登录淘宝");
        setContentView(R.layout.activity_main);

        //移除所有Cookie
        CookieManager.getInstance().removeAllCookie();

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl("https://h5.m.taobao.com/ww/index.htm");

        mScanCookieHandler = new ScanCookieHandler();
        mScanCookieHandler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.destroy();

        if (mScanCookieHandler != null)
        {
            mScanCookieHandler.removeCallbacksAndMessages(null);
            mScanCookieHandler = null;
        }
    }

    private void onLoginSuccess()
    {
        Launcher.launchToManagementActivity(this);
        finish();
    }

    private class ScanCookieHandler extends Handler
    {
        @Override
        public void dispatchMessage(Message msg)
        {
            super.dispatchMessage(msg);
            CookieManager cookieManager = CookieManager.getInstance();
            String cookieString = cookieManager.getCookie("api.m.taobao.com");
            if (cookieString != null && cookieString.contains("_m_h5_tk"))
            {
                //登录已成功
                this.removeCallbacksAndMessages(null);
                onLoginSuccess();
            }
            else
            {
                this.sendEmptyMessageDelayed(1, 1000);
            }
        }
    }
}
