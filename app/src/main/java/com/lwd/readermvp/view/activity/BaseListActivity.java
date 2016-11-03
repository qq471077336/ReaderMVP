package com.lwd.readermvp.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lwd.readermvp.R;
import com.lwd.readermvp.presenter.BaseListPresenter;
import com.lwd.readermvp.presenter.TopMoviePresenter;
import com.lwd.readermvp.view.impl.BaseListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseListActivity<T> extends BaseActivity implements BaseListView<T>, SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_list)
    RecyclerView       mRv;
    @BindView(R.id.srl_list)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.ll_no_network)
    LinearLayout       mNoNetwork;
    @BindView(R.id.tv_retry)
    TextView           mRetry;

    protected BaseQuickAdapter mAdapter;
    private View          notLoadingView;
    private View          mEmptyView;

    @Override
    public void bindView() {
        mRv.setLayoutManager(new LinearLayoutManager(this));
        List<T> subjects = new ArrayList<>();
//        mAdapter = new Top250Adapter(R.layout.item_top250, subjects);
        initAdapter(subjects);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRv.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadMore(10);

        mSrl.setOnRefreshListener(this);

        mEmptyView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRv.getParent(), false);

        mEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseListPresenter) mPresenter).getContent(true);
            }
        });

        ((BaseListPresenter) mPresenter).getContent(true);
    }

    public abstract void initAdapter(List<T> subjects);

    @Override
    public int getLayout() {
        return R.layout.list_layout;
    }

    @Override
    public void notifyChanged(List<T> subjects, boolean isRefresh) {
        if (isRefresh)
            mAdapter.setNewData(subjects);
        else
            mAdapter.addData(subjects);
    }

    @Override
    public void refreshComplete() {
        mSrl.setRefreshing(false);
    }

    @Override
    public void notLoadingMore() {
        if (notLoadingView == null) {
            notLoadingView = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) mRv.getParent(), false);
        }
        mAdapter.addFooterView(notLoadingView);
        mAdapter.loadComplete();
    }

    @Override
    public void empty() {
        mAdapter.setEmptyView(mEmptyView);
    }

    @Override
    public void error() {
        mAdapter.showLoadMoreFailedView();
    }

    @Override
    public void noNetwork(boolean hasNet) {
        mRetry.setEnabled(true);
        mNoNetwork.setVisibility(hasNet ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.tv_retry)
    public void retry() {
        mRetry.setEnabled(false);
        ((BaseListPresenter) mPresenter).getContent(true);
    }

    @Override
    public void onRefresh() {
        ((BaseListPresenter) mPresenter).getContent(true);
        mAdapter.openLoadMore(10);
        mAdapter.removeAllFooterView();
    }

    @Override
    public void onLoadMoreRequested() {
        ((TopMoviePresenter) mPresenter).getContent(false);
    }
}
