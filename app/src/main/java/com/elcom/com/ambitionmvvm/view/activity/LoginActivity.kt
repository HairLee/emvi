package com.elcom.com.ambitionmvvm.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.elcom.com.ambitionmvvm.R
import com.elcom.com.ambitionmvvm.databinding.LoginLayoutBinding
import com.elcom.com.ambitionmvvm.viewModel.Login.LoginViewModel
import java.util.*

class LoginActivity : BaseActivity<LoginLayoutBinding>(), Observer {


    private var loginViewModel = LoginViewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.login_layout)
        binding.isLoading = true
        setUpObserver(loginViewModel)
        loginViewModel.fetchUsersList()

    }




    fun fillDetail(context: Context): Intent {
        val intent = Intent(context, LoginActivity::class.java)
        return intent
    }

    private fun setUpObserver(observable: Observable) {
        observable.addObserver(this)
    }

    override fun update(p0: Observable?, p1: Any?) {
//       val mList =  loginViewModel.getData()
//       binding.isLoading = false
        val intent = Intent(this, TestSocketActivity::class.java)
        startActivity(intent)
    }
}
