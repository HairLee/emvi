package com.elcom.com.ambitionmvvm.viewModel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.elcom.com.ambitionmvvm.model.User
import com.elcom.com.ambitionmvvm.view.activity.LoginActivity

/**
 * Created by Hailpt on 4/3/2018.
 */
class ItemUserViewModel(private var user: User?, private val context: Context) : BaseObservable() {

    val profileThumb: String
        get() = user!!.picture.large!!

    val cell: String
        get() = user!!.cell!!

    val email: String
        get() = user!!.email!!

    val fullName: String
        get() {

            user!!.fullName = user!!.email

            return user!!.fullName!!

        }

    fun onItemClick(v: View) {
        context.startActivity(LoginActivity().fillDetail(v.context))
    }

    fun setUser(user: User) {
        this.user = user
        notifyChange()
    }

    companion object {

        // Loading Image using Glide Library.
        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}
