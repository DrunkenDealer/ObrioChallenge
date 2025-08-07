package io.obrio.challange.core.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtils {

    private const val DEFAULT_PATTER = "yyyy-MM-dd"

    fun millsToFormattedDate(mills: Long): String {
        return millsToLocalDate(mills).format(DateTimeFormatter.ofPattern(DEFAULT_PATTER))
    }

    private fun millsToLocalDate(mills: Long): LocalDate {
        return Instant.ofEpochMilli(mills).atZone(ZoneId.systemDefault()).toLocalDate()
    }
}