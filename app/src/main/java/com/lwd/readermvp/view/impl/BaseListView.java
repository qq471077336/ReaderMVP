package com.lwd.readermvp.view.impl;

import com.lwd.readermvp.view.BaseView;

import java.util.List;

/**
 * Created by lianweidong on 2016/10/19.
 */

public interface BaseListView<T> extends BaseView {

    void notifyChanged(List<T> subjects, boolean isRefresh);

    void refreshComplete();

    void notLoadingMore();

    void empty();

    void error();

    void noNetwork(boolean hasNet);
}
