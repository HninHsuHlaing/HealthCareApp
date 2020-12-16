package com.padcx.shared

import android.app.Application
import com.padcx.shared.data.model.impl.HealthCareModelImpl

/**
 * Created by Hnin Hsu Hlaing
 * on 11/26/2020
 */
class healthCareApp : Application() {
    override fun onCreate() {
        super.onCreate()
        HealthCareModelImpl.initDatabase(applicationContext)
    }
}