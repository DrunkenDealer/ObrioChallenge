package io.obrio.challange.ui.features.balance.data

import io.obrio.challange.ui.features.balance.enum.TransactionCategory
import io.obrio.challange.ui.features.balance.enum.TransactionType
import java.math.BigDecimal

data class Transaction(
    val id: String,
    val amount: BigDecimal,
    val category: TransactionCategory,
    val time: String,
    val type: TransactionType
)
