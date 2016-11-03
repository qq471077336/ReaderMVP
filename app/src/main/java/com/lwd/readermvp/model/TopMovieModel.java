package com.lwd.readermvp.model;

import com.lwd.readermvp.api.Top250Method;
import com.lwd.readermvp.bean.Top250;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lianweidong on 2016/10/17.
 */

public class TopMovieModel implements BaseListModel<Top250> {

    @Override
    public void getContent(Subscriber<Top250> subscriber, int start, int count) {
        Top250Method.getInstance()
                .getTop250Service()
                .getTop250(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
