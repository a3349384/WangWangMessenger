package cn.zmy.wangwangmessenger.message.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import cn.zmy.wangwangmessenger.BR;

/**
 * Created by zmy on 2017/9/23.
 */

public class MessageManagementModel extends BaseObservable
{
    private String message;
    @Bindable
    private String progressLog;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getProgressLog()
    {
        return progressLog;
    }

    public void setProgressLog(String progressLog)
    {
        this.progressLog = progressLog;
        notifyPropertyChanged(BR.progressLog);
    }
}
