package com.lwd.readermvp.view.activity;

import com.lwd.readermvp.R;
import com.lwd.readermvp.adapter.Top250Adapter;
import com.lwd.readermvp.bean.Top250;
import com.lwd.readermvp.model.TopMovieModel;
import com.lwd.readermvp.presenter.BasePresenter;
import com.lwd.readermvp.presenter.TopMoviePresenter;

import java.util.List;

public class TopMovieActivity extends BaseListActivity<Top250.subject> {

    @Override
    public void initAdapter(List<Top250.subject> subjects) {
        mAdapter = new Top250Adapter(R.layout.item_top250, subjects);
    }

    @Override
    public BasePresenter getPresenter() {
        return new TopMoviePresenter(new TopMovieModel());
    }
}
