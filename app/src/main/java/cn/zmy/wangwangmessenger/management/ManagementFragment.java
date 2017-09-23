package cn.zmy.wangwangmessenger.management;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import cn.zmy.common.base.IToolbatAware;
import cn.zmy.common.binding.fragment.BaseBindingFragment;
import cn.zmy.wangwangmessenger.BR;
import cn.zmy.wangwangmessenger.R;
import cn.zmy.wangwangmessenger.base.helper.CookieHelper;
import cn.zmy.wangwangmessenger.management.viewmodel.ManagementViewModel;
import cn.zmy.wangwangmessenger.manager.CookieManager;
import cn.zmy.wangwangmessenger.taobao.helper.TaobaoHelper;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        String h5tk = TaobaoHelper.getH5tk();
        if (TextUtils.isEmpty(h5tk))
        {
            getActivity().finish();
            return;
        }
        CookieManager.getInstance().setH5tk(h5tk);
        CookieManager.getInstance().setHttpCookies(CookieHelper.stringToHttpCookies(CookieHelper.getTaobaoWebCookiesString()));
    }

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
