package com.lwd.readermvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.lwd.readermvp.IBase;
import com.lwd.readermvp.presenter.BasePresenter;
import com.lwd.readermvp.view.BaseView;

import butterknife.ButterKnife;

/**
 * Created by lianweidong on 2016/10/15.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBase {

    protected BasePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(this).inflate(getLayout(), null);
        setContentView(view);

        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.subcribe((BaseView) this);
        }

        ButterKnife.bind(this, view);

        bindView();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.unsubcribe();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
