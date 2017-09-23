package cn.zmy.wangwangmessenger.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.health.UidHealthStats;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cn.zmy.common.base.IToolbatAware;
import cn.zmy.common.base.provider.RefreshProvider;
import cn.zmy.common.base.provider.SwipeRefreshProvider;
import cn.zmy.common.binding.adapter.BaseBindingAdapter;
import cn.zmy.common.binding.fragment.BaseBindListFragment;
import cn.zmy.common.utils.ToastUtil;
import cn.zmy.wangwangmessenger.R;
import cn.zmy.wangwangmessenger.databinding.ItemUserBinding;
import cn.zmy.wangwangmessenger.user.adapter.UserListAdapter;
import cn.zmy.wangwangmessenger.user.daoservice.UserDaoService;
import cn.zmy.wangwangmessenger.user.model.User;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by zmy on 2017/9/21.
 */

public class UserManagementFragment extends BaseBindListFragment<User> implements IToolbatAware, Toolbar.OnMenuItemClickListener
{
    public static final UserManagementFragment newInstance()
    {
        return new UserManagementFragment();
    }

    private UserListAdapter mUserListAdapter;
    private UserDaoService mUserDaoService;

    public UserManagementFragment()
    {
        mUserListAdapter = new UserListAdapter();
        mUserDaoService = new UserDaoService();
    }

    @Override
    public void setToolbar(Toolbar toolbar)
    {
        toolbar.setTitle("用户管理");
        toolbar.inflateMenu(R.menu.menu_user_management);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuAddUser:
            {
                this.onAddUserClick();
                break;
            }
        }
        return true;
    }

    @Override
    protected BaseBindingAdapter onCreateBindingAdapter()
    {
        return mUserListAdapter;
    }

    @Override
    protected List<User> getItems(int i)
    {
        return mUserDaoService.list();
    }

    @Override
    protected RefreshProvider onCreateRefreshProvider()
    {
        return new SwipeRefreshProvider();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }
        Uri uri = intent.getData();
        if (uri == null)
        {
            return;
        }
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        Observable.create(new Observable.OnSubscribe<String>()
        {
            @Override
            public void call(Subscriber<? super String> subscriber)
            {
                try
                {
                    InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    String userName;
                    while ((userName = reader.readLine()) != null)
                    {
                        subscriber.onNext(userName);
                    }
                    reader.close();
                    subscriber.onCompleted();
                }
                catch (Exception ex)
                {
                    subscriber.onError(ex);
                }
            }
        }).filter(s -> !TextUtils.isEmpty(s.trim())).map(User::of)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> progressDialog.show())
                .subscribe(new Subscriber<User>()
                {
                    List<User> users = new ArrayList<>();

                    @Override
                    public void onCompleted()
                    {
                        progressDialog.dismiss();
                        ToastUtil.showShort(getContext(), "共导入" + users.size() + "条数据");
                        mUserListAdapter.getItems().addAll(users);
                        mUserDaoService.insert(users);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        progressDialog.dismiss();
                        ToastUtil.showShort(getContext(), "导入失败：" + e.getMessage());
                    }

                    @Override
                    public void onNext(User user)
                    {
                        users.add(user);
                        progressDialog.setMessage("已导入" + users.size() + "条数据");
                    }
                });
    }

    private void onAddUserClick()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }
}
