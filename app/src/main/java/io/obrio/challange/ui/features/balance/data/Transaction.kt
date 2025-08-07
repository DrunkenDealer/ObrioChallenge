package io.obrio.challange.ui.features.balance.data

import io.obrio.challange.ui.features.balance.enum.TransactionCategory
import io.obrio.challange.ui.features.balance.enum.TransactionType

data class Transaction(
    val id: Long,
    val amount: Double,
    val category: TransactionCategory,
    val timestamp: Long,
    val type: TransactionType
)
