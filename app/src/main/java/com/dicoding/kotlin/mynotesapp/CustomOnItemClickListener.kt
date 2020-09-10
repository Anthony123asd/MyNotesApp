package com.dicoding.kotlin.mynotesapp

import android.view.View
import javax.security.auth.callback.Callback

class CustomOnItemClickListener(private val position: Int, private val onItemClickCallback: OnItemClickCallback) : View.OnClickListener{
    override fun onClick(v: View?) {
        onItemClickCallback.onItemClicked(v!!, position)
    }

    interface OnItemClickCallback{
        fun onItemClicked(view: View, position: Int)
    }
}