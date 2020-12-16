package com.padcx.shared.data.vo.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vo.SenderVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
class SenderVOConverter{
    @TypeConverter
    fun toString(dataList: SenderVO):String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr:String): SenderVO {
        val dataListType = object : TypeToken<SenderVO>(){}.type
        return Gson().fromJson(ListJsonStr,dataListType)
    }
}