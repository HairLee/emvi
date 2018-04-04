package com.elcom.com.ambitionmvvm.viewModel

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.elcom.com.ambitionmvvm.AppController
import com.elcom.com.ambitionmvvm.R
import com.elcom.com.ambitionmvvm.Utils.Constant.Companion.RANDOM_USER_URL
import com.elcom.com.ambitionmvvm.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*

/**
 * Created by Hailpt on 4/3/2018.
 */
class UserViewModel(pContext: Context) : Observable() {

    lateinit var progressBar: ObservableInt
    lateinit var userRecycler: ObservableInt
    lateinit var userLabel: ObservableInt
    lateinit var messageLabel: ObservableField<String>

    private var userList: MutableList<User>? = null
    private var context = pContext
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    public fun UserViewModel() {
        userList = ArrayList()
        progressBar = ObservableInt(View.GONE)
        userRecycler = ObservableInt(View.GONE)
        userLabel = ObservableInt(View.VISIBLE)
        messageLabel = ObservableField(context.getString(R.string.default_message_loading_users))
    }

    fun onClickFabToLoad(view: View) {
        initializeViews()
        fetchUsersList()
    }

    //It is "public" to show an example of test
    fun initializeViews() {
        userLabel.set(View.GONE)
        userRecycler.set(View.GONE)
        progressBar.set(View.VISIBLE)
    }

    private fun fetchUsersList() {

        val appController = AppController.create(this.context!!)
        val usersService = appController.userService

        val disposable = usersService!!.fetchUsers(RANDOM_USER_URL)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userResponse  ->
                    updateUserDataList(userResponse.peopleList!!)
                    progressBar.set(View.GONE)
                    userLabel.set(View.GONE)
                    userRecycler.set(View.VISIBLE)
                }, {
                    messageLabel.set(context!!.getString(R.string.error_message_loading_users))
                    progressBar.set(View.GONE)
                    userLabel.set(View.VISIBLE)
                    userRecycler.set(View.GONE)
                })

        compositeDisposable!!.add(disposable)
    }

    private fun updateUserDataList(peoples: List<User>) {
        userList!!.addAll(peoples) // update userList for getUserList()
        setChanged()
        notifyObservers() // to inform "update" - MainActivity know that, the data is changed, and then get new userList from getUserList()
    }

    fun getUserList(): List<User> {
        return userList!!
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed()) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null

    }

}