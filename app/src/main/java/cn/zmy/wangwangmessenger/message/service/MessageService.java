package cn.zmy.wangwangmessenger.message.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cn.zmy.wangwangmessenger.manager.UserManager;
import cn.zmy.wangwangmessenger.user.model.User;
import cn.zmy.wangwangmessenger.taobao.httpservice.MessageHttpService;

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
            for (User user : UserManager.getInstance().getUsers())
            {
                MessageHttpService.sendMessage(user.getName(), "在不在");
                try
                {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
