package com.example.mvpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.example.mvpdemo.base.BaseAct;
import com.example.mvpdemo.base.BaseView;
import com.example.mvpdemo.presenter.PicturePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseAct<BaseView, PicturePresenter> implements BaseView {

    @BindView(R.id.bt_token)
    Button btToken;
    private PicturePresenter picturePresenter;

    @Override
    public int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public PicturePresenter getPresenter() {
        picturePresenter = new PicturePresenter(this, this);
        return picturePresenter;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void loadSuccess(Object data) {
        Log.e("Access token-----", (String) data);
    }

    @Override
    public void loadBefore(int message) {

    }

    @Override
    public void loadAfter() {

    }

    @Override
    public void loadFailed(String message) {

    }

    @OnClick(R.id.bt_token)
    public void onViewClicked() {
        picturePresenter.getToken();
    }
}
