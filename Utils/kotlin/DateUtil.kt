package com.example.utils

import java.text.SimpleDateFormat
import java.util.*


const val YMDHM = "yyyy-MM-dd HH:mm"
const val YMDHMS = "yyyy-MM-dd HH:mm:ss"
const val HM = "HH:mm"
const val Y_M_D = "yyyy-MM-dd"
const val WEEK = "EEEE"
const val YMD = "yyyyMMdd"
const val Y_M = "yyyy-MM"
const val HMS = "HH:mm:ss"
const val Y_Y = "yyyy"
const val M_M = "MM"

/**
 * date 类型转为 Long
 */
fun dateToLong(date: Date): Long {
    return date.time
}

/**
 * String 类型转 Date 类型
 */
fun stringToDate(time: String, timeType: String): Date? {
    var date: Date? = null
    try {
        val format = SimpleDateFormat(timeType, Locale.CHINA)
        date = format.parse(time)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return date
}

/**
 * 日期 String  转 Long
 */
fun stringToLong(time: String, timeType: String): Long {
    val date = stringToDate(time, timeType)
    return if (date == null) {
        0
    } else {
        dateToLong(date)
    }

}

/**
 * 格式化时间 Date - String
 */
fun formatDate(date: Date, timeType: String): String {
    val sdf = SimpleDateFormat(timeType, Locale.CHINA)
    return sdf.format(date)
}
