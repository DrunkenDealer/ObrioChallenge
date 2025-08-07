package io.obrio.challange.repository.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class BitcoinRateResponse(
    @Json(name = "Bitcoin")
    val bitcoin: Usd
) {

    @JsonClass(generateAdapter = true)
    data class Usd(
        val usd: BigDecimal
    )
}
