package com.padcx.shared.data.vo.converters

import androidx.room.TypeConverter
import com.google.firebase.Timestamp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
class TimeStampCOnverter {
    @TypeConverter
    fun toString(dataList: Timestamp):String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr:String): Timestamp {
        val dataListType = object : TypeToken<Timestamp>(){}.type
        return Gson().fromJson(ListJsonStr,dataListType)
    }
}