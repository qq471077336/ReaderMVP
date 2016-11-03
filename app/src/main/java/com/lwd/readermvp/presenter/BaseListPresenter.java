package com.lwd.readermvp.presenter;

import com.lwd.readermvp.bean.Top250;
import com.lwd.readermvp.model.BaseListModel;
import com.lwd.readermvp.view.impl.BaseListView;

import rx.Subscriber;

/**
 * Created by lianweidong on 2016/10/19.
 */

public class BaseListPresenter<M extends BaseListModel> extends BasePresenter<BaseListView> {

    private M mModel;
    private int start = 0;
    private int count = 10;

    public BaseListPresenter(M model) {
        mModel = model;
    }

    public void getContent(final boolean isRefresh) {

        if (mView == null)

        start = isRefresh ? 0 : start;

        mModel.getContent(new Subscriber<Top250>() {
            @Override
            public void onCompleted() {
                if (mView == null){return;}
                mView.noNetwork(true);
                mView.refreshComplete();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null){return;}
                e.printStackTrace();
                mView.refreshComplete();
                if (start > 0)
                    mView.error();
                else
                    mView.noNetwork(false);
            }

            @Override
            public void onNext(Top250 top250) {
                if (mView == null){return;}
                if (top250.subjects.size() == 0 && start == 0) {
                    mView.empty();
                } else {
                    start = (top250.start + top250.subjects.size());

                    mView.notifyChanged(top250.subjects, isRefresh);

                    if ((top250.start + count) >= top250.total)
                        mView.notLoadingMore();
                }
            }
        }, start, count);
    }
}
