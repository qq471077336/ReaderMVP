package com.lwd.readermvp.model;

import rx.Subscriber;

/**
 * Created by lianweidong on 2016/10/19.
 */

public interface BaseListModel<T> extends BaseModel {

    void getContent(Subscriber<T> subscriber, int start, int count);
}
