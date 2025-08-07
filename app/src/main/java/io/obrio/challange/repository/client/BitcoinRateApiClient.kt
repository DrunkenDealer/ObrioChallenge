package io.obrio.challange.repository.client

import io.obrio.challange.BuildConfig
import io.obrio.challange.repository.api.BitcoinRateApiInterface
import io.obrio.challange.repository.model.BitcoinRateResponse
import javax.inject.Inject

class BitcoinRateApiClient @Inject constructor(
    private val bitcoinRateApiInterface: BitcoinRateApiInterface
) {

    fun getBitcoinRate(): BitcoinRateResponse {
        return bitcoinRateApiInterface.getBitcoinRate(
            header = BuildConfig.API_KEY,
            currency = "usd",
            name = "bitcoin",
            precision = 2
        )
    }
}