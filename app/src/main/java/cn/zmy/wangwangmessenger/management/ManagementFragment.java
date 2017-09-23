package cn.zmy.wangwangmessenger.management;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.zmy.common.base.IToolbatAware;
import cn.zmy.common.binding.fragment.BaseBindingFragment;
import cn.zmy.wangwangmessenger.BR;
import cn.zmy.wangwangmessenger.R;
import cn.zmy.wangwangmessenger.management.viewmodel.ManagementViewModel;

/**
 * Created by zmy on 2017/9/21.
 */

public class ManagementFragment extends BaseBindingFragment implements IToolbatAware
{
    public static final ManagementFragment newInstance()
    {
        return new ManagementFragment();
    }

    private ManagementViewModel mVM;

    @Override
    public void setToolbar(Toolbar toolbar)
    {
        toolbar.setTitle("管理");
        toolbar.setNavigationIcon(null);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_management;
    }

    @Override
    protected void onBindingCreated(ViewDataBinding binding)
    {
        mVM = new ManagementViewModel(getActivity());
        binding.setVariable(BR.vm, mVM);
        binding.executePendingBindings();
    }
}
