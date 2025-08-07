package io.obrio.challange.repository.api

import io.obrio.challange.repository.model.BitcoinRateResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BitcoinRateApiInterface {

    @GET("/")
    fun getBitcoinRate(
        @Header("x-cg-demo-api-key") header: String,
        @Query("vs_currencies") currency: String,
        @Query("name") name: String,
        @Query("precision") precision: Int
    ): BitcoinRateResponse
}