package io.obrio.challange.ui.features.add_transaction.mvi

import io.obrio.challange.ui.features.balance.enums.TransactionCategory
import java.math.BigDecimal

data class AddTransactionState(
    val bitcoinRate: BigDecimal = BigDecimal.ZERO,
    val bitcoinsAmount: BigDecimal = BigDecimal.ZERO,
    val usdBalance: String = "",
    val categories: List<TransactionCategory> = emptyList(),
    val enteredAmount: String = "",
    val canAddTransaction: Boolean = true
)