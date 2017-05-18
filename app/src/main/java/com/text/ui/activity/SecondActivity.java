package com.text.ui.activity;

import android.widget.TextView;

import com.text.R;
import com.text.bean.SecondActivityEvent;
import com.text.config.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZCYL on 2017/5/16.
 */

public class SecondActivity extends BaseActivity {

    @BindView(R.id.activity_two_text)
    TextView textView;

    @Override
    public int getId() {
        return R.layout.activity_two;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    /**
     * 粘性事件，传递进来
     * @param event 自定义的事件，只会接受该类型的事件，默认还可以接收其子类的事件
     */
    @Subscribe(sticky = true)
    public void onActivityGet(SecondActivityEvent event){
        textView.setText(event.getMsg());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //注释掉下面这句，将会产生泄漏
        EventBus.getDefault().unregister(this);
    }
}
