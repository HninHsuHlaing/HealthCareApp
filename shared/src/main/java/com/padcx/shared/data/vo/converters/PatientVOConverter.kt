package com.padcx.shared.data.vo.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vo.PatientVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
class PatientVOConverter {
    @TypeConverter
    fun toString(dataList: PatientVO):String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr:String): PatientVO {
        val dataListType = object : TypeToken<PatientVO>(){}.type
        return Gson().fromJson(ListJsonStr,dataListType)
    }
}