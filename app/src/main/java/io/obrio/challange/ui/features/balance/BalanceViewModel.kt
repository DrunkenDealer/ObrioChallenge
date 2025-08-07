package io.obrio.challange.ui.features.balance

import dagger.hilt.android.lifecycle.HiltViewModel
import io.obrio.challange.core.base.BaseViewModel
import io.obrio.challange.core.extensions.formattedPrice
import io.obrio.challange.core.utils.DateUtils
import io.obrio.challange.data.entity.BitcoinTransactionEntity
import io.obrio.challange.network.client.BitcoinRateApiClient
import io.obrio.challange.repository.BitcoinRepository
import io.obrio.challange.ui.features.balance.data.Transaction
import io.obrio.challange.ui.features.balance.data.TransactionGroup
import io.obrio.challange.ui.features.balance.enums.TransactionCategory
import io.obrio.challange.ui.features.balance.manager.BitcoinRateCacheManager
import io.obrio.challange.ui.features.balance.mvi.BalanceEffect
import io.obrio.challange.ui.features.balance.mvi.BalanceIntent
import io.obrio.challange.ui.features.balance.mvi.BalanceState
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val cacheManager: BitcoinRateCacheManager,
    private val bitcoinRepository: BitcoinRepository,
    private val bitcoinRateApiClient: BitcoinRateApiClient
): BaseViewModel<BalanceState, BalanceEffect>(
    BalanceState()
), BalanceIntent {

    init {
        initState()
        initObserves()
    }

    private var currentPage = 0
    private val pageSize = 20

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

    fun updateBalanceAndTransactions() {
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
                updateUI(balance, it)
            }

            reloadTransactions()
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
                updateUI(balance, it)
            }

            loadMoreTransactions()
        }
    }

    override fun updateBalance(amount: Double) {
        viewModelScope.launch {
            val rate = cacheManager.getLastRate()?.toBigDecimal() ?: BigDecimal.ZERO
            val newBalance = bitcoinRepository.updateBalanceAndReturn(amount.toBigDecimal())

            bitcoinRepository.addTransaction(
                amount.toBigDecimal(),
                TransactionCategory.TOPUP.name
            )

            updateUI(newBalance, rate)
            reloadTransactions()
        }
    }

    private fun loadMoreTransactions() {
        if (state.value.isLoadingTransactions || !state.value.hasMoreTransactions) return

        viewModelScope.launch {
            updateState { copy(isLoadingTransactions = true) }

            val newItems = bitcoinRepository.getPagedTransactions(
                page = currentPage,
                pageSize = pageSize
            )
            val combinedRaw = state.value.rawTransactions + newItems

            updateState {
                copy(
                    rawTransactions = combinedRaw,
                    transactions = mapTransactions(combinedRaw),
                    isLoadingTransactions = false,
                    hasMoreTransactions = newItems.size == pageSize
                )
            }

            currentPage++
        }
    }

    private fun reloadTransactions() {
        viewModelScope.launch {
            currentPage = 0

            val newItems = bitcoinRepository.getPagedTransactions(
                page = 0,
                pageSize = pageSize
            )

            updateState {
                copy(
                    rawTransactions = newItems,
                    transactions = mapTransactions(newItems),
                    hasMoreTransactions = newItems.size == pageSize
                )
            }

            currentPage++
        }
    }

    private fun mapTransactions(transactions: List<BitcoinTransactionEntity>): List<TransactionGroup> {
        val grouped = transactions
            .sortedByDescending { it.timestamp }
            .groupBy {
                DateUtils.formatDefaultDate(Date(it.timestamp))
            }

        return grouped.map { (date, list) ->
            val displayDate = when (date) {
                DateUtils.getTodayStringDate() -> "Today"
                DateUtils.getYesterdayStringDate() -> "Yesterday"
                else -> date
            }

            TransactionGroup(
                dateLabel = displayDate,
                transactions = list.map {
                    Transaction(
                        id = it.id,
                        amount = it.amount,
                        category = TransactionCategory.parse(it.category),
                        time = DateUtils.formatDateTime(Date(it.timestamp)),
                    )
                }
            )
        }
    }


    private fun updateUI(balance: BigDecimal, rate: BigDecimal) {
        updateState {
            copy(
                bitcoinRate = rate.formattedPrice(),
                bitcoinsAmount = balance.toPlainString(),
                usdBalance = (balance * rate).formattedPrice()
            )
        }
    }
}