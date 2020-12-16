package com.padcx.shared.activities

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
abstract class BaseeDialogFragment : DialogFragment(), BaseView
{
    override fun showError(error: String) {
        //   Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }
    inline fun <reified T : AbstractBaseePresenter<W>, reified W: BaseView> getPresenter(): T {
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W) presenter.initPresenter(this)
        return presenter
    }
}