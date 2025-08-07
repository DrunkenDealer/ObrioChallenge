package io.obrio.challange.ui.features.balance.mvi

import io.obrio.challange.ui.features.balance.data.Transaction

data class BalanceState(
    val bitcoinRate: String = "",
    val bitcoinsAmount: String = "",
    val usdBalance: String = "",
    val transactions: List<Transaction> = emptyList()
)
