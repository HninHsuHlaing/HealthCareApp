package com.padcx.shared.data.vo.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vo.DeliveryRoutineVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
class DeliveryRoutineConverter {
    @TypeConverter
    fun toString(dataList: DeliveryRoutineVO):String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr:String): DeliveryRoutineVO {
        val dataListType = object : TypeToken<DeliveryRoutineVO>(){}.type
        return Gson().fromJson(ListJsonStr,dataListType)
    }
}