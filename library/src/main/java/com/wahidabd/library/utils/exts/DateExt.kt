package com.wahidabd.library.utils.exts

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

const val DATE_TIME_GLOBAL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DATE_TIME_STANDARD = "yyyy-MM-dd HH:mm:ss"
const val DATE_TIME_STANDARD_WITH_MS = "yyyy-MM-dd HH:mm:ss.SSS"
const val DEFAULT_PATTERN = "dd.MM.yyyy"
const val DEFAULT_DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm"
const val DISPLAY_PATTERN = "dd MM yyyy"
const val MONTH_DATE_PATTERN = "MMMM dd, yyyy"
const val DEFAULT_TIME_PATTERN = "HH:mm"
const val ZERO_ZERO_TIME = "00:00"
const val DAY_DATE_FORMAT = "EEEE, MMMM dd yyyy"

fun concatDateTime(date: String, time: String): String =
    "$date $time"

@RequiresApi(Build.VERSION_CODES.O)
fun getDayNow(): String = LocalDateTime.now().dayOfWeek.name

fun getFirstDayOfLastMonth(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(calendar[1], calendar.get(2 - 1), 1)
    return calendar.time
}