package com.elcom.com.ambitionmvvm.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;


/**
 *
 *
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    B binding;

    /**
     * ViewModel must be initialized before bindView() is called
     * @param layout layout for the activity
     */
    protected final void bindView(int layout) {
        binding = DataBindingUtil.setContentView(this, layout);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
