package io.obrio.challange.ui.features.balance.data

import io.obrio.challange.ui.features.balance.enums.TransactionCategory
import java.math.BigDecimal

data class Transaction(
    val id: String,
    val amount: BigDecimal,
    val category: TransactionCategory,
    val time: String
)
