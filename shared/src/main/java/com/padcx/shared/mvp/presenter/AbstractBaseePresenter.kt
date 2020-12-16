package com.padcx.shared.mvp.presenter

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 11/24/2020
 */
abstract class AbstractBaseePresenter <T : BaseView> : BasePresenter<T>, ViewModel() {
    protected lateinit var mView: T

    override fun initPresenter(view: T) {
        mView = view
    }

    fun sendEventsToFirebaseAnalytics(
        context: Context,
        eventName: String,
        key: String = "",
        value: String = ""
    ) {
        if(key.isNotEmpty() && value.isNotEmpty()){
            FirebaseAnalytics.getInstance(context).logEvent(eventName, buildBundle(key, value))
        } else {
            FirebaseAnalytics.getInstance(context).logEvent(eventName, null)
        }
    }

    private fun buildBundle(key: String, value: String): Bundle {
        val bundle = Bundle()
        bundle.putString(key, value)
        return bundle
    }
}