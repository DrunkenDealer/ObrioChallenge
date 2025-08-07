package io.obrio.challange.repository.client

import io.obrio.challange.repository.api.BitcoinRateApiInterface
import io.obrio.challange.repository.model.BitcoinRateResponse
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