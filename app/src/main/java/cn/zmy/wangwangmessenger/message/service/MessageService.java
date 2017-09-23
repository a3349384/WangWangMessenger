package cn.zmy.wangwangmessenger.message.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;
import java.util.Random;

import cn.zmy.common.utils.Util;
import cn.zmy.wangwangmessenger.base.constant.IntentConstant;
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
                return;
            }
            if (isInterrupted())
            {
                return;
            }
            for (User user : users)
            {
                if (isInterrupted())
                {
                    Log.d("MessageThread", "消息线程中断");
                    break;
                }
                Log.d("MessageThread", "准备发送消息给：" + user.getName());
                MessageSendResponse response = MessageHttpService.sendMessage(user.getName(), mMessage);
                if (response != null && response.isSuccess())
                {
                    Log.d("MessageThread", "发送消息成功");
                    user.setStatus(User.STATUS_SUCCESS);
                }
                else
                {
                    Log.d("MessageThread", "发送消息失败");
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
        }
    }
}
