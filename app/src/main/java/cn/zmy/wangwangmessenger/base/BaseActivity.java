package cn.zmy.wangwangmessenger.base;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import cn.zmy.common.base.ToolbarFragmentActivity;
import cn.zmy.wangwangmessenger.R;

/**
 * Created by zmy on 2017/9/21.
 */

public abstract class BaseActivity extends ToolbarFragmentActivity
{
    @Override
    protected Toolbar onCreateToolbar()
    {
        Toolbar toolbar = super.onCreateToolbar();
        toolbar.setBackgroundResource(R.color.colorPrimary);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setNavigationOnClickListener((view) -> onBackPressed());
        //menu color 在value/styles中通过actionMenuTextColor设置
        return toolbar;
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
