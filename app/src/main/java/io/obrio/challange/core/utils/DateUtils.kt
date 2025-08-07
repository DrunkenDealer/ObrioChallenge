package io.obrio.challange.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    private const val DEFAULT_PATTER = "yyyy-MM-dd"

    fun getTodayStringDate(): String {
        return SimpleDateFormat(DEFAULT_PATTER, Locale.getDefault()).format(
            Date(System.currentTimeMillis())
        )
    }

    fun getYesterdayStringDate(): String {
        return SimpleDateFormat(DEFAULT_PATTER, Locale.getDefault()).format(
            Date(System.currentTimeMillis() - 86400000)
        )
    }

    fun formatDefaultDate(date: Date): String {
        return SimpleDateFormat(DEFAULT_PATTER, Locale.getDefault()).format(date)
    }

    fun formatDateTime(date: Date): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
    }
}