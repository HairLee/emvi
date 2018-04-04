package com.elcom.com.ambitionmvvm.viewModel.Login

import android.content.Context
import android.view.View
import com.elcom.com.ambitionmvvm.AppController
import com.elcom.com.ambitionmvvm.model.User
import com.elcom.com.ambitionmvvm.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Hailpt on 4/4/2018.
 */
class LoginViewModel(pContext: Context) : BaseViewModel(pContext) {

     fun fetchUsersList() {

        val appController = AppController.create(this.mContext!!)
        val usersService = appController.userService

        val disposable = usersService!!.fetchUsers()
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userResponse  ->
                    updateUserDataList(userResponse.peopleList)


                }, {



                })

        compositeDisposable!!.add(disposable)
    }

    private var mList : List<User>? = null
    private fun updateUserDataList(peoples: List<User>) {
        mList = peoples
        setChanged()
        notifyObservers()
    }

    fun getData(): List<User>{

        return mList!!
    }

    fun onClickFabToLoad(view: View) {
        fetchUsersList()
    }

}