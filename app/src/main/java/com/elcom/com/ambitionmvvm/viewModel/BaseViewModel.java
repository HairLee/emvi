package com.elcom.com.ambitionmvvm.viewModel;

import android.content.Context;

import java.util.Observable;

import io.reactivex.disposables.CompositeDisposable;

/**
 *
 *
 */

public class BaseViewModel extends Observable {

    protected CompositeDisposable compositeDisposable;

    public Context mContext;

    public BaseViewModel(Context pContext) {
        mContext = pContext;
        compositeDisposable = new CompositeDisposable();
    }

    public void clearSubscriptions() {
        compositeDisposable.clear();
    }

}
