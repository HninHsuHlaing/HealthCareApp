package com.padcx.shared.data.vo.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.data.vo.RoutineVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
class PrescriptionVOConverter {
    @TypeConverter
    fun toString(dataList: ArrayList<PrescriptionVO>):String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr:String): ArrayList<PrescriptionVO> {
        val dataListType = object : TypeToken<ArrayList<RoutineVO>>(){}.type
        return Gson().fromJson(ListJsonStr,dataListType)
    }
}