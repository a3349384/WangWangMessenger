package cn.zmy.wangwangmessenger.message;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;

import cn.zmy.common.base.IToolbatAware;
import cn.zmy.common.binding.fragment.BaseBindingFragment;
import cn.zmy.wangwangmessenger.BR;
import cn.zmy.wangwangmessenger.R;
import cn.zmy.wangwangmessenger.message.viewmodel.MessageManageViewModel;

/**
 * Created by zmy on 2017/9/23.
 */

public class MessageManagementFragment extends BaseBindingFragment implements IToolbatAware
{
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
        mVM = new MessageManageViewModel();
        viewDataBinding.setVariable(BR.vm, mVM);
    }
}
