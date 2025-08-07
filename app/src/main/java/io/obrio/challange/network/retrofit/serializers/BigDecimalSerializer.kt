package io.obrio.challange.network.retrofit.serializers

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal

object BigDecimalSerializer {
    @FromJson
    fun fromJson(double: Double) = double.toBigDecimal()

    @ToJson
    fun toJson(value: BigDecimal) = value.toDouble()
}