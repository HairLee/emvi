package com.elcom.com.ambitionmvvm.network;

import com.elcom.com.ambitionmvvm.utils.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hailpt on 4/3/2018.
 */
public class ApiFactory {

    public static UsersService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(UsersService.class);
    }

}
