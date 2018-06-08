package com.example.mvpdemo.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.example.mvpdemo.base.BasePresenter;
import com.example.mvpdemo.base.BaseView;
import com.example.mvpdemo.mange.RetrofitClient;
import com.example.mvpdemo.mange.RxSchedulerHepler;
import com.example.mvpdemo.model.AccessTokenBean;

import java.io.ByteArrayOutputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PicturePresenter extends BasePresenter<BaseView> {

    private BaseView baseView;
    private Context context;
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String API_KEY = "TEqrH3q466ljIIHPQVLsfnem";
    private static final String SECRET_KEY = "jCPvzEGRqvBXAGd9aPqCgBLR8iGfeRTW";
    private String ACCESS_TOKEN = "";

    public PicturePresenter(BaseView view, Context context){
        this.baseView = view;
        this.context = context;
    }

    @Override
    public boolean addRx(Disposable disposable) {
        return super.addRx(disposable);
    }

    public void getToken(){

        RetrofitClient.getInstance(context)
                .provideApiService()
                .getAccessToken(CLIENT_CREDENTIALS,API_KEY,SECRET_KEY)
                .compose(RxSchedulerHepler.<AccessTokenBean>io_main())
                .subscribe(new Observer<AccessTokenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AccessTokenBean accessTokenBean) {
                        ACCESS_TOKEN = accessTokenBean.getAccess_token();
                        baseView.loadSuccess(accessTokenBean.getAccess_token());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
