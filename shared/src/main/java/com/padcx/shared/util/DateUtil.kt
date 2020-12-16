package com.padcx.shared.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
class DateUtil {
    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return calendar.time
    }

    fun getCurrentDate() : String{
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
        return simpleDateFormat.format(Date())
    }
    fun getCurrentDateTime() : String{
        return SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date())
    }

    fun getCurrentHourMinAMPM() : String{
        return SimpleDateFormat("hh:mm a").format(Date())
    }
}