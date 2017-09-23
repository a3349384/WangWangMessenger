package cn.zmy.wangwangmessenger;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.zmy.wangwangmessenger.adapter.UserListAdapter;
import cn.zmy.wangwangmessenger.helper.CookieHelper;
import cn.zmy.wangwangmessenger.helper.TaobaoHelper;
import cn.zmy.wangwangmessenger.manager.CookieManager;
import cn.zmy.wangwangmessenger.user.model.User;

/**
 * Created by zmy on 2017/9/20.
 */

public class MessageActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int REQUEST_CODE_GET_FILE = 1;

    private RecyclerView mRecyclerView;
    private Button mButtonAddUser;
    private Button mButtonStart;
    private Button mButtonStop;
    private UserListAdapter mUserListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("消息");

        String h5tk = TaobaoHelper.getH5tk();
        if (TextUtils.isEmpty(h5tk))
        {
            finish();
            return;
        }
        CookieManager.getInstance().setH5tk(h5tk);
        CookieManager.getInstance().setHttpCookies(CookieHelper.parseCookieString());

        setContentView(R.layout.activity_meaasge);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mButtonAddUser = (Button) findViewById(R.id.buttonAddUser);
        mButtonStart = (Button) findViewById(R.id.buttonStart);
        mButtonStop = (Button) findViewById(R.id.buttonStop);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        mRecyclerView.addItemDecoration(divider);

        mUserListAdapter = new UserListAdapter();
        mRecyclerView.setAdapter(mUserListAdapter);

        mButtonAddUser.setOnClickListener(this);
        mButtonStart.setOnClickListener(this);
        mButtonStop.setOnClickListener(this);
        mButtonStop.setEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode != RESULT_OK)
        {
            return;
        }
        if (requestCode == REQUEST_CODE_GET_FILE)
        {

        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.buttonAddUser:
            {

                break;
            }
            case R.id.buttonStart:
            {
                mButtonAddUser.setEnabled(false);
                mButtonStart.setEnabled(false);
                mButtonStop.setEnabled(true);

                startService(new Intent(this, MessageService.class));
                break;
            }
            case R.id.buttonStop:
            {
                mButtonAddUser.setEnabled(true);
                mButtonStart.setEnabled(true);
                mButtonStop.setEnabled(false);
                break;
            }
        }
    }
}
