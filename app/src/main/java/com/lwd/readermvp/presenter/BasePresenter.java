package com.lwd.readermvp.presenter;

import com.lwd.readermvp.view.BaseView;

/**
 * Created by lianweidong on 2016/10/14.
 */

public abstract class BasePresenter<T extends BaseView> {

    public T mView;

//    protected BaseModel mModel;

    public void subcribe(T view) {
        this.mView = view;
    }

    public void unsubcribe() {
        if (this.mView != null) {
            this.mView = null;
        }
    }
}
