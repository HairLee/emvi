package com.elcom.com.ambitionmvvm

import android.app.Application
import android.content.Context
import com.elcom.com.ambitionmvvm.network.ApiFactory
import com.elcom.com.ambitionmvvm.network.UsersService
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by Hailpt on 4/3/2018.
 */
class AppController : Application() {

    var userService: UsersService? = null

        get() {
            if (field == null) {
                userService = ApiFactory.create()
            }

            return field
        }
    private var scheduler: Scheduler? = null

    fun subscribeScheduler(): Scheduler {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }

        return this.scheduler!!
    }

    fun setScheduler(scheduler: Scheduler) {
        this.scheduler = scheduler
    }

    companion object {

        private operator fun get(context: Context): AppController {
            return context.applicationContext as AppController
        }

        fun create(context: Context): AppController {
            return AppController[context]
        }
    }

}