package com.example.mvpdemo.base;

public abstract interface BaseView {

    /**
     * 加载数据成功
     * @param data
     */
    void loadSuccess(Object data);

    /**
     * 加载数据前
     * @param message
     */
    void loadBefore(int message);

    /**
     * 加载数据后
     */
    void loadAfter();

    /**
     * 加载失败
     * @param message
     */
    void loadFailed(String message);
}
