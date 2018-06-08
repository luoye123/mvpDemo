package com.example.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseAct<V extends BaseView,T extends BasePresenter<V>>
                extends AppCompatActivity {

    private T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId(savedInstanceState));
        presenter = getPresenter();
        initView();
    }

    /**
     * 返回layout资源文件
     * @param savedInstanceState
     * @return
     */
    public abstract int getLayoutId(@Nullable Bundle savedInstanceState);

    /**
     * 初始化
     */
    public void initView(){
        ButterKnife.bind(this);
    }

    /**
     * 获取presenter
     * @return
     */
    public abstract T getPresenter();

    @Override
    protected void onResume() {
        super.onResume();
        if(null != presenter){
            presenter.onAttach((V)this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != presenter){
            presenter.onDetch();
        }
    }

}
