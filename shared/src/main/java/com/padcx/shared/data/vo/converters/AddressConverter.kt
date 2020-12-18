package com.padcx.shared.data.vo.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class AddressConverter {
    @TypeConverter
    fun toString(dataList: ArrayList<String>): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): ArrayList<String> {
        val dataListType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }

}