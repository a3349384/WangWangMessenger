package cn.zmy.wangwangmessenger.base;

import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.zmy.wangwangmessenger.base.constant.LauncherConstant;
import cn.zmy.wangwangmessenger.management.ManagementFragment;
import cn.zmy.wangwangmessenger.message.MessageManagementFragment;
import cn.zmy.wangwangmessenger.user.UserManagementFragment;

/**
 * Created by zmy on 2017/9/21.
 */

public class LauncherActivity extends BaseActivity
{
    @Override
    protected Fragment onCreateFragment()
    {
        switch (getLauncherConstant())
        {
            case LauncherConstant.LAUNCH_TO_MANAGEMENT_ACTIVITY:
            {
                return ManagementFragment.newInstance();
            }
            case LauncherConstant.LAUNCH_TO_USER_MANAGEMENT_ACTIVITY:
            {
                return UserManagementFragment.newInstance();
            }
            case LauncherConstant.LAUNCH_TO_MESSAGE_MANAGEMENT_ACTIVITY:
            {
                return MessageManagementFragment.newInstance();
            }
            default:
            {
                return null;
            }
        }
    }

    private int getLauncherConstant()
    {
        Intent intent = getIntent();
        if (intent == null)
        {
            return -1;
        }
        return intent.getIntExtra(LauncherConstant.LAUNCHER_CONSTANT_KEY, -1);
    }
}
