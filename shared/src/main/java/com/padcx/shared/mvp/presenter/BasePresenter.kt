package com.padcx.shared.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 11/24/2020
 */
interface BasePresenter <T : BaseView>{
    fun initPresenter(view: T)
    fun onUiReady(context: Context, owner: LifecycleOwner)
}