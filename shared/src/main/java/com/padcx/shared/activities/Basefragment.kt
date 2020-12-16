package com.padcx.shared.activities

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/10/2020
 */
abstract class Basefragment :Fragment(), BaseView {
    override fun showError(error: String) {

    }
    inline fun <reified T : AbstractBaseePresenter<W>, reified W: BaseView> getPresenter(): T {
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W) presenter.initPresenter(this)
        return presenter
    }
}