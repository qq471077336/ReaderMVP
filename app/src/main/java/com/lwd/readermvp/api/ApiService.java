package com.lwd.readermvp.api;

import com.lwd.readermvp.bean.Top250;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 访问网络
 * <p>
 * Created by lianweidong on 2016/10/15.
 */

public interface ApiService {

    @GET("top250")
    Observable<Top250> getTop250(@Query("start") int start,
                                 @Query("count") int count);

}
