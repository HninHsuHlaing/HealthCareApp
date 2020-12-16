package com.padcx.shared.data.vo.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vo.DoctorVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
class DoctorVOConverter {
    @TypeConverter
    fun toString(dataList: DoctorVO):String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr:String): DoctorVO {
        val dataListType = object : TypeToken<DoctorVO>(){}.type
        return Gson().fromJson(ListJsonStr,dataListType)
    }
}