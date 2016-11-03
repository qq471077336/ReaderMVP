package com.lwd.readermvp.view.impl;

import com.lwd.readermvp.bean.Top250;
import com.lwd.readermvp.view.BaseView;

import java.util.List;

/**
 * Created by lianweidong on 2016/10/18.
 */

public interface Top250View extends BaseView {

    void notifyChanged(List<Top250.subject> subjects, boolean isRefresh);

    void refreshComplete();

    void notLoadingMore();

    void empty();

    void error();

    void noNetwork(boolean hasNet);

}
