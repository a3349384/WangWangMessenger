package cn.zmy.wangwangmessenger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import cn.zmy.wangwangmessenger.adapter.UserListAdapter;
import cn.zmy.wangwangmessenger.model.User;

/**
 * Created by zmy on 2017/9/20.
 */

public class MessageActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private UserListAdapter mUserListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("消息");

        setContentView(R.layout.activity_meaasge);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        mRecyclerView.addItemDecoration(divider);

        mUserListAdapter = new UserListAdapter();
        mRecyclerView.setAdapter(mUserListAdapter);

        User user = new User();
        user.setName("黑屋炫酷无痕");
        user.setDesc("等待发送");
        mUserListAdapter.getItems().add(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuAdd:
            {
                break;
            }
        }
        return true;
    }
}
