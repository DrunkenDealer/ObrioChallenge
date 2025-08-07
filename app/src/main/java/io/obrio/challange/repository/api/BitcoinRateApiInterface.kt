package io.obrio.challange.repository.api

import io.obrio.challange.repository.model.BitcoinRateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BitcoinRateApiInterface {

    @GET("api/v3/simple/price")
    suspend fun getBitcoinRate(
        @Query("vs_currencies") currency: String,
        @Query("names") name: String,
        @Query("precision") precision: Int
    ): BitcoinRateResponse
}