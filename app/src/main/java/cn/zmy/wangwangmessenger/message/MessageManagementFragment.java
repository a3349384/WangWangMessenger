package cn.zmy.wangwangmessenger.message;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.zmy.common.base.IToolbatAware;
import cn.zmy.common.binding.fragment.BaseBindingFragment;
import cn.zmy.wangwangmessenger.BR;
import cn.zmy.wangwangmessenger.R;
import cn.zmy.wangwangmessenger.message.model.MessageProgress;
import cn.zmy.wangwangmessenger.message.viewmodel.MessageManageViewModel;

/**
 * Created by zmy on 2017/9/23.
 */

public class MessageManagementFragment extends BaseBindingFragment implements IToolbatAware
{
    public static final String TAG = "Message";

    public static MessageManagementFragment newInstance()
    {
        return new MessageManagementFragment();
    }

    private MessageManageViewModel mVM;

    @Override
    public void setToolbar(Toolbar toolbar)
    {
        toolbar.setTitle("消息管理");
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_meaasge_management;
    }

    @Override
    protected void onBindingCreated(ViewDataBinding viewDataBinding)
    {
        mVM = new MessageManageViewModel(getActivity());
        viewDataBinding.setVariable(BR.vm, mVM);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mVM.stop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProgressLog(MessageProgress progress)
    {
        if (progress != null)
        {
            Log.d(TAG, progress.getProgressLog());
            mVM.getModel().setProgressLog(progress.getProgressLog());
        }
    }
}
