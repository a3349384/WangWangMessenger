package cn.zmy.wangwangmessenger.adapter;

import cn.zmy.common.binding.adapter.SingleTypeBindingAdapter;
import cn.zmy.wangwangmessenger.BR;
import cn.zmy.wangwangmessenger.R;
import cn.zmy.wangwangmessenger.model.User;

/**
 * Created by zmy on 2017/9/20.
 */

public class UserListAdapter extends SingleTypeBindingAdapter<User>
{
    @Override
    protected int getItemLayout()
    {
        return R.layout.item_user;
    }

    @Override
    protected int getModelId()
    {
        return BR.model;
    }
}
