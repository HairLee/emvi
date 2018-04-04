package com.elcom.com.ambitionmvvm.view.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.elcom.com.ambitionmvvm.R
import com.elcom.com.ambitionmvvm.databinding.ActivityMainBinding
import com.elcom.com.ambitionmvvm.view.adapter.UserAdapter
import com.elcom.com.ambitionmvvm.viewModel.Main.UserViewModel

import java.util.Observable
import java.util.Observer

class MainActivity : AppCompatActivity(), Observer {


    private var userActivityBinding: ActivityMainBinding? = null
    private var userViewModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setUpListOfUsersView(userActivityBinding!!.listUser)
        setUpObserver(userViewModel!!)
    }

    private fun initDataBinding() {
        userActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userViewModel = UserViewModel(this)
        userViewModel!!.UserViewModel()
        (userActivityBinding as ActivityMainBinding).userViewModel = userViewModel
    }

    // set up the list of user with recycler view
    private fun setUpListOfUsersView(listUser: RecyclerView) {
        val userAdapter = UserAdapter()
        listUser.adapter = userAdapter
        listUser.layoutManager = LinearLayoutManager(this)
    }

    fun setUpObserver(observable: Observable) {
        observable.addObserver(this)
    }

    override fun update(o: Observable?, p1: Any?) {
        if (o is UserViewModel) {
            val userAdapter = userActivityBinding!!.listUser.adapter as UserAdapter  // get Current adapter to put new data
            userAdapter.setUserList(o.getUserList()) // put new data into adapter to update item
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        userViewModel!!.reset()
    }


}
