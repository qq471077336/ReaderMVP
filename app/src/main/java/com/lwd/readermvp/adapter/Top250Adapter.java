package com.lwd.readermvp.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lwd.readermvp.R;
import com.lwd.readermvp.bean.Top250;

import java.util.List;

/**
 * Created by lianweidong on 2016/10/18.
 */

public class Top250Adapter extends BaseQuickAdapter<Top250.subject> {

    public Top250Adapter(int layoutResId, List<Top250.subject> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Top250.subject subject) {
        StringBuilder mCharacters = new StringBuilder();
        for (int i = 0; i < subject.directors.size(); i++) {
            mCharacters.append(subject.directors.get(i).name).append("(导演)/");
        }
        for (int i = 0; i < subject.casts.size(); i++) {
            mCharacters.append(subject.casts.get(i).name);
            if (i != subject.casts.size() - 1)
                mCharacters.append("/");
        }
        StringBuilder   sbGenres = new StringBuilder();
        sbGenres.append("类型：");
        for (int i = 0; i < subject.genres.length; i++) {
            sbGenres.append(subject.genres[i]);
            if (i != subject.genres.length - 1)
                sbGenres.append("/");
        }
        viewHolder.setText(R.id.tv_name, subject.title + " " + subject.original_title)
                .setText(R.id.tv_rating, subject.rating.average + "")
                .setText(R.id.tv_collect_count, subject.collect_count + "收藏")
                .setText(R.id.tv_directors_casts, mCharacters.toString())
                .setText(R.id.tv_genres, sbGenres.toString())
                .setRating(R.id.rb_rating, subject.rating.average, subject.rating.max)
                .setText(R.id.tv_year, subject.year + "年上映");

        Glide.with(mContext)
                .load(subject.images.large)
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into((ImageView) viewHolder.getView(R.id.iv_poster));
    }
}
