package io.obrio.challange.ui.features.balance

import dagger.hilt.android.lifecycle.HiltViewModel
import io.obrio.challange.core.base.BaseViewModel
import io.obrio.challange.repository.client.BitcoinRateApiClient
import io.obrio.challange.ui.features.balance.manager.BitcoinRateCacheManager
import io.obrio.challange.ui.features.balance.mvi.BalanceEffect
import io.obrio.challange.ui.features.balance.mvi.BalanceIntent
import io.obrio.challange.ui.features.balance.mvi.BalanceState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val cacheManager: BitcoinRateCacheManager,
    private val bitcoinRateApiClient: BitcoinRateApiClient
): BaseViewModel<BalanceState, BalanceEffect>(
    BalanceState()
), BalanceIntent {

    init {
        initState()
        initObserves()
    }


    private fun initObserves() = with(viewModelScope) {
        launch {
            getErrorFlow().collect(::handleError)
        }
    }

    private fun handleError(error: Throwable) {
        error.message?.let { message ->
            publishEffect(BalanceEffect.ShowToast(message))
        }
    }

    private fun initState() {
        viewModelScope.launch {
            if (cacheManager.shouldFetch()) {
                val rate = bitcoinRateApiClient.getBitcoinRate().bitcoin.usd.toPlainString()
                updateState {
                    copy(bitcoinRate = rate)
                }
                cacheManager.updateFetchTimestampAndRate(rate)
            } else {
                cacheManager.getLastRate()?.let { rate ->
                    updateState {
                        copy(bitcoinRate = rate)
                    }
                }
            }
        }
    }

    override fun addBalance() {
        TODO("Not yet implemented")
    }

    override fun addTransaction() {
        TODO("Not yet implemented")
    }
}