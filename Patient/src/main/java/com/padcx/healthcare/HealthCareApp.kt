package com.padcx.healthcare

import android.app.Application
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.data.model.impl.HealthCareModelImpl

/**
 * Created by Hnin Hsu Hlaing
 * on 11/24/2020
 */
class HealthCareApp : Application() {
    override fun onCreate() {
        super.onCreate()
        HealthCareModelImpl.initDatabase(applicationContext)
        SessionManager.init(applicationContext)
    }
}