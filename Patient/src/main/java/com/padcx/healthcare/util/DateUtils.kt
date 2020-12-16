package com.padcx.healthcare.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/11/2020
 */
class DateUtils {
    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return calendar.time
    }

    fun getCurrentDate() : String{
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
        return simpleDateFormat.format(Date())
    }
}