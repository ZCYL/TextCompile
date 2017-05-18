package com.text.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.text.R;
import com.text.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZCYL on 2017/5/16.
 */

public class Fragment1 extends Fragment {

    MessageEvent event;
    @BindView(R.id.fragment_text_textview)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this,view);
//        textView = (TextView) view.findViewById(R.id.fragment_text_textview);
        return view;
    }
    public Fragment1() {
    }

    @SuppressLint("ValidFragment")
    public Fragment1(MessageEvent event) {
        this.event = event;
    }

    protected int getLayoutId() {
        return R.layout.fragement_text;
    }

    @OnClick(R.id.fragment_text_bn)
    public void clickShow(View v){
        Toast.makeText(getContext(), "fragment 点击", Toast.LENGTH_SHORT).show();
        /**
         * 发送时间给activity，通知其更换fragment
         */
        EventBus.getDefault().post(getClass().getName());
    }

    /**
     * 表示普通事件的交互
     * @param event
     */
    @Subscribe
    public void eventGet(MessageEvent event){
        textView.setText(event.getName() + "\n" + event.getMessage());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        textView.setText(event.getMessage() + "\n" + event.getName());
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
