package com.padcx.shared.mvp.presenter.impl

import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import com.padcx.shared.mvp.presenter.BasePresenter
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
abstract class BaseAppPresenterImpl<V : BaseView> : AbstractBaseePresenter<V>(), BasePresenter<V> {
}