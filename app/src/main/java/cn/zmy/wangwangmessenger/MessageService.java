package cn.zmy.wangwangmessenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import cn.zmy.wangwangmessenger.model.MessageData;
import cn.zmy.wangwangmessenger.service.MessageHttpService;

/**
 * Created by zmy on 2017/9/20.
 */

public class MessageService extends Service
{
    private MessageThread mMessageThread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (mMessageThread == null)
        {
            mMessageThread = new MessageThread();
        }

        if (mMessageThread.getState() == Thread.State.NEW)
        {
            mMessageThread.start();
        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    static class MessageThread extends Thread
    {
        @Override
        public void run()
        {
            MessageHttpService.sendMessage("黑屋炫酷无痕", "在不在");
        }
    }
}
