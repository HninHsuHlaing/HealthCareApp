package com.padcx.healthcare.mvp.presenter

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.padcx.healthcare.mvp.view.ProfileView
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface ProfilePresenter :BasePresenter<ProfileView>{
    fun onUiReadyForAccountFragment(context : Context, owner: LifecycleOwner)

    fun updateUserData(bitmap: Bitmap,
                       blood_type : String, dateofbirth : String, height : String, comment: String, phone: String)
}