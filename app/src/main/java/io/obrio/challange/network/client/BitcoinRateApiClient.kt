package io.obrio.challange.network.client

import io.obrio.challange.network.api.BitcoinRateApiInterface
import io.obrio.challange.network.model.BitcoinRateResponse
import javax.inject.Inject

class BitcoinRateApiClient @Inject constructor(
    private val bitcoinRateApiInterface: BitcoinRateApiInterface
) {

    suspend fun getBitcoinRate(): BitcoinRateResponse {
        return bitcoinRateApiInterface.getBitcoinRate(
            currency = "usd",
            name = "bitcoin",
            precision = 2
        )
    }
}