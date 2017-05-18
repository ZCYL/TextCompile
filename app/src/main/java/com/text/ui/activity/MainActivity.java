package com.text.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.text.R;
import com.text.bean.MessageEvent;
import com.text.bean.SecondActivityEvent;
import com.text.config.BaseActivity;
import com.text.ui.fragment.Fragment1;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.activity_main_bn)
    Button bnChange;
    AtomicInteger integer = new AtomicInteger();

    @Override
    public int getId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        switchFragment(integer.incrementAndGet() + "");
    }

    void switchFragment(String text) {
        MessageEvent event = new MessageEvent();
        event.setMessage(text);
        event.setName(getClass().getSimpleName() + " to " + Fragment1.class.getSimpleName());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.activity_main_fg, new Fragment1(event));
        transaction.commit();
    }

    @OnClick(R.id.activity_main_bn)
    public void clickChange(View view) {
        /**
         *发送普通消息，只用当前注册了该事件的订阅者才能够接收到消息
         */
        EventBus.getDefault().post(new MessageEvent(integer.incrementAndGet() + "", "activity 传递事件"));
        /**
         *发送粘性事件，当前注册了该事件的订阅者能够接收到消息
         * 此外，该事件会被EventBus保存，当有监听器注册并且方法的声明包括{@Subscribe(sticky = true)}
         * 方法会被调用
         * 注意：EventBus只会保存最新的一条消息，会把原来的消息覆盖掉
         */
        EventBus.getDefault().postSticky(new SecondActivityEvent(integer.get() + ""));
        Toast.makeText(this, "activity 点击事件", Toast.LENGTH_SHORT).show();

    }

    @Subscribe(sticky = true)
    public void eventGet(MessageEvent event){
        bnChange.setText(event.getName() + "\n" + event.getMessage());
    }

    @Subscribe
    public void changeFg(String i){
        switchFragment(i + "");
    }

    @OnClick(R.id.activity_main_bn_next)
    public void clickToNextActivity(View v){
        startActivity(new Intent(this,SecondActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
