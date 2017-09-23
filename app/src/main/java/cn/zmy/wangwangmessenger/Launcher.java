package cn.zmy.wangwangmessenger;

import android.content.Context;
import android.content.Intent;

import cn.zmy.wangwangmessenger.base.LauncherActivity;
import cn.zmy.wangwangmessenger.constant.LauncherConstant;

/**
 * Created by zmy on 2017/9/21.
 */

public class Launcher
{
    public static void launchToManagementActivity(Context context)
    {
        commonStart(context, LauncherConstant.LAUNCH_TO_MANAGEMENT_ACTIVITY);
    }

    public static void launchToUserManagementActivity(Context context)
    {
        commonStart(context, LauncherConstant.LAUNCH_TO_USER_MANAGEMENT_ACTIVITY);
    }

    private static void commonStart(Context context, int launchWhich)
    {
        Intent intent = new Intent(context, LauncherActivity.class);
        intent.putExtra(LauncherConstant.LAUNCHER_CONSTANT_KEY, launchWhich);

        context.startActivity(intent);
    }
}
