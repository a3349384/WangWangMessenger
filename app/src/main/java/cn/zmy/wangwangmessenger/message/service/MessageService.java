package cn.zmy.wangwangmessenger.message.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

import cn.zmy.common.utils.Util;
import cn.zmy.wangwangmessenger.base.constant.IntentConstant;
import cn.zmy.wangwangmessenger.message.model.MessageProgress;
import cn.zmy.wangwangmessenger.taobao.model.MessageSendResponse;
import cn.zmy.wangwangmessenger.user.daoservice.UserDaoService;
import cn.zmy.wangwangmessenger.user.model.User;
import cn.zmy.wangwangmessenger.taobao.httpservice.MessageHttpService;

/**
 * Created by zmy on 2017/9/20.
 */

public class MessageService extends Service
{
    private MessageThread mMessageThread;

    public static void start(Context context, String message)
    {
        if (TextUtils.isEmpty(message))
        {
            return;
        }
        Intent intent = new Intent(context, MessageService.class);
        intent.putExtra(IntentConstant.INTENT_KEY_MESSAGE, message);
        context.startService(intent);
    }

    public static void stop(Context context)
    {
        Intent intent = new Intent(context, MessageService.class);
        context.stopService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (mMessageThread == null)
        {
            mMessageThread = new MessageThread(intent.getStringExtra(IntentConstant.INTENT_KEY_MESSAGE));
            mMessageThread.start();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mMessageThread != null)
        {
            mMessageThread.interrupt();
            mMessageThread = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    static class MessageThread extends Thread
    {
        private String mMessage;
        private Random mRandom;
        private UserDaoService mUserDaoService;

        public MessageThread(String message)
        {
            this.mMessage = message;
            mRandom = new Random(System.currentTimeMillis());
            mUserDaoService = new UserDaoService();
        }

        @Override
        public void run()
        {
            //获取需要发送消息的用户
            List<User> users = mUserDaoService.list(User.STATUS_NONE);
            if (Util.getCollectSize(users) == 0)
            {
                sendProgressLog("找不到需要发送消息的用户");
                return;
            }
            if (isInterrupted())
            {
                sendProgressLog("停止发送");
                return;
            }
            for (User user : users)
            {
                if (isInterrupted())
                {
                    sendProgressLog("停止发送");
                    break;
                }
                sendProgressLog("准备发送消息给：" + user.getName());
                MessageSendResponse response = MessageHttpService.sendMessage(user.getName(), mMessage);
                if (response != null && response.isSuccess())
                {
                    sendProgressLog("成功发送消息给：" + user.getName());
                    user.setStatus(User.STATUS_SUCCESS);
                }
                else
                {
                    sendProgressLog("发送消息失败：" + user.getName());
                    user.setStatus(User.STATUS_FAILED);
                }
                mUserDaoService.update(user);
                int sleepTime = 2048 + mRandom.nextInt(3000);
                try
                {
                    Log.d("MessageThread", "sleep:" + sleepTime);
                    Thread.sleep(sleepTime);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                    break;
                }
            }
            sendProgressLog("发送完毕");
        }

        private void sendProgressLog(String log)
        {
            if (TextUtils.isEmpty(log))
            {
                return;
            }
            MessageProgress progress = new MessageProgress();
            progress.setProgressLog(log);
            EventBus.getDefault().post(progress);
        }
    }
}
