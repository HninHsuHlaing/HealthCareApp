package com.padcx.doctor.mvp.presenter

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface ProfilePresenter {

    fun onUiReadyForProfile(context : Context, owner: LifecycleOwner)

    fun updateUserData(bitmap: Bitmap,
                       specialitname : String, speciality : String,
                       phone : String, degree : String,
                       bigraphy : String, address : String,
                       experience : String, dateofbirth : String,
                       gender: String)
}