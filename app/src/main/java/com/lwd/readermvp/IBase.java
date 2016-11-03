package com.lwd.readermvp;

import android.support.annotation.LayoutRes;

import com.lwd.readermvp.presenter.BasePresenter;

/**
 * Created by lianweidong on 2016/10/15.
 */

public interface IBase {

    void bindView();

    @LayoutRes int getLayout();

    BasePresenter getPresenter();
}
