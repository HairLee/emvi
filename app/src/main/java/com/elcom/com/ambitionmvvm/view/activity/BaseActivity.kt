package com.elcom.com.ambitionmvvm.view.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity

/**
 * Created by Hailpt on 4/4/2018.
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    internal lateinit var binding: B

    /**
     * ViewModel must be initialized before bindView() is called
     * @param layout layout for the activity
     */
    protected fun bindView(layout: Int) {
        binding = DataBindingUtil.setContentView(this, layout)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}