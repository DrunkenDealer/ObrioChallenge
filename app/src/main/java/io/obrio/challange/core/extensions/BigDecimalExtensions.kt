package io.obrio.challange.core.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.formattedPrice(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return formatter.format(this)
}

fun BigDecimal.isPositive(): Boolean {
    return this > BigDecimal.ZERO
}