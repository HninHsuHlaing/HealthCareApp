package com.padcx.shared.data.vo.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vo.QuestionAndAnswerVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
class CaseSummaryConverter {
    @TypeConverter
    fun toString(dataList: ArrayList<QuestionAndAnswerVO>):String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr:String): ArrayList<QuestionAndAnswerVO> {
        val dataListType = object : TypeToken<ArrayList<QuestionAndAnswerVO>>(){}.type
        return Gson().fromJson(ListJsonStr,dataListType)
    }
}