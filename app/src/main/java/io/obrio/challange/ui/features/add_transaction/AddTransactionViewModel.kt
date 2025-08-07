package io.obrio.challange.ui.features.add_transaction

import dagger.hilt.android.lifecycle.HiltViewModel
import io.obrio.challange.core.base.BaseViewModel
import io.obrio.challange.core.extensions.formattedPrice
import io.obrio.challange.network.client.BitcoinRateApiClient
import io.obrio.challange.repository.BitcoinRepository
import io.obrio.challange.ui.features.add_transaction.mvi.AddTransactionEffect
import io.obrio.challange.ui.features.add_transaction.mvi.AddTransactionIntent
import io.obrio.challange.ui.features.add_transaction.mvi.AddTransactionState
import io.obrio.challange.ui.features.balance.enums.TransactionCategory
import io.obrio.challange.ui.features.balance.manager.BitcoinRateCacheManager
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val bitcoinRepository: BitcoinRepository,
    private val cacheManager: BitcoinRateCacheManager,
    private val bitcoinRateApiClient: BitcoinRateApiClient
): BaseViewModel<AddTransactionState, AddTransactionEffect>(AddTransactionState()), AddTransactionIntent {

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
            publishEffect(AddTransactionEffect.ShowToast(message))
        }
    }

    private fun initState() {
        viewModelScope.launch {
            val balance = bitcoinRepository.getBalance()

            val rate = if (cacheManager.shouldFetch()) {
                bitcoinRateApiClient.getBitcoinRate().bitcoin.usd.also {
                    cacheManager.updateFetchTimestampAndRate(it.toPlainString())
                }
            } else {
                cacheManager.getLastRate()?.toBigDecimal()
            }

            rate?.let {
                updateState {
                    copy(
                        bitcoinRate = it,
                        bitcoinsAmount = balance,
                        usdBalance = (it * balance).formattedPrice(),
                        categories = listOf(
                            TransactionCategory.GROCERIES,
                            TransactionCategory.TAXI,
                            TransactionCategory.ELECTRONICS,
                            TransactionCategory.RESTAURANT,
                            TransactionCategory.OTHER
                        )
                    )
                }
            }
        }
    }

    override fun addTransaction(amount: Double, category: TransactionCategory) {
        viewModelScope.launch {
            val negativeAmount = amount.toBigDecimal().negate()
            bitcoinRepository.updateBalanceAndReturn(negativeAmount)
            bitcoinRepository.addTransaction(negativeAmount, category.name)
        }
    }

    override fun onAmountChange(amount: String) {
        val parsed = amount.toBigDecimalOrNull()
        val usd = parsed?.multiply(state.value.bitcoinRate)?.formattedPrice().orEmpty()
        val canAdd = parsed?.let { it <= state.value.bitcoinsAmount } ?: false

        updateState {
            copy(
                enteredAmount = amount,
                usdBalance = usd,
                canAddTransaction = canAdd
            )
        }
    }
}