package io.obrio.challange.ui.features.balance.mvi

import io.obrio.challange.data.entity.BitcoinTransactionEntity
import io.obrio.challange.ui.features.balance.data.TransactionGroup

data class BalanceState(
    val bitcoinRate: String = "",
    val bitcoinsAmount: String = "0",
    val usdBalance: String = "",
    val rawTransactions: List<BitcoinTransactionEntity> = emptyList(),
    val transactions: List<TransactionGroup> = emptyList(),
    val isLoadingTransactions: Boolean = false,
    val hasMoreTransactions: Boolean = true
)
