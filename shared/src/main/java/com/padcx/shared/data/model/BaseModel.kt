package com.padcx.shared.data.model

import android.content.Context
import com.padcx.shared.persistent.HealthCareDatabase

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
abstract class BaseModel {
    protected lateinit var mTheDB: HealthCareDatabase


    fun initDatabase(context: Context) {
        mTheDB = HealthCareDatabase.getDBInstance(context)
    }
}