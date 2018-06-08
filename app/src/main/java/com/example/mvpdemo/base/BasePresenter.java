package com.example.mvpdemo.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<T> {

    private CompositeDisposable disposables;// 管理Destroy取消订阅者者

    T view;

    /**
     * 获取view实例
     * @param view
     */
    void onAttach(T view){
        this.view = view;
    }

    public boolean addRx(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
        return true;
    }

    /**
     * 解绑view
     */
    void onDetch(){
        this.view = null;
        if (disposables != null) {
            disposables.dispose();
            disposables = null;
        }
    }
}
