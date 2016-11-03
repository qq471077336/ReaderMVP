package com.lwd.readermvp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwd.readermvp.IBase;
import com.lwd.readermvp.presenter.BasePresenter;
import com.lwd.readermvp.view.BaseView;

import butterknife.ButterKnife;

/**
 * Created by lianweidong on 2016/10/15.
 */
public abstract class BaseFragment extends Fragment implements IBase {

    private BasePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayout(), container, false);

        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.subcribe((BaseView) this);
        }

        ButterKnife.bind(this, view);

        bindView();

        return view;
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.unsubcribe();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
