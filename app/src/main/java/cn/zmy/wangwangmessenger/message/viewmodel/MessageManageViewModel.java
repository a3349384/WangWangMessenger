package cn.zmy.wangwangmessenger.message.viewmodel;

import android.content.Context;

import cn.zmy.common.utils.KeyboardUtil;
import cn.zmy.wangwangmessenger.message.model.MessageManagementModel;
import cn.zmy.wangwangmessenger.message.service.MessageService;

/**
 * Created by zmy on 2017/9/23.
 */

public class MessageManageViewModel
{
    private Context mActivityContext;
    private MessageManagementModel mModel;

    public MessageManageViewModel(Context activityContext)
    {
        this.mActivityContext = activityContext;
        mModel = new MessageManagementModel();
    }

    public MessageManagementModel getModel()
    {
        return mModel;
    }

    public void setModel(MessageManagementModel model)
    {
        this.mModel = model;
    }

    public void start()
    {
        MessageService.start(mActivityContext, mModel.getMessage());
    }

    public void stop()
    {
        MessageService.stop(mActivityContext);
    }
}
