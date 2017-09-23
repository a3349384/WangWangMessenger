package cn.zmy.wangwangmessenger.management.viewmodel;

import android.content.Context;

import cn.zmy.wangwangmessenger.base.Launcher;

/**
 * Created by zmy on 2017/9/21.
 */

public class ManagementViewModel
{
    private Context mContext;

    public ManagementViewModel(Context context)
    {
        this.mContext = context;
    }

    public void onUserManagementClick()
    {
        Launcher.launchToUserManagementActivity(mContext);
    }

    public void onMessageManagementClick()
    {
        Launcher.launchToMessageManagementActivity(mContext);
    }
}
