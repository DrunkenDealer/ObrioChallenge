package io.obrio.challange.ui.features.add_transaction.mvi

import androidx.compose.runtime.Stable
import io.obrio.challange.ui.features.balance.enums.TransactionCategory

@Stable
interface AddTransactionIntent {

    fun addTransaction(amount: Double, category: TransactionCategory)
    fun onAmountChange(amount: String)

    companion object {
        val Empty: AddTransactionIntent = EmptyAddTransactionIntent
    }
}

private object EmptyAddTransactionIntent : AddTransactionIntent {
    override fun addTransaction(amount: Double, category: TransactionCategory) = Unit
    override fun onAmountChange(amount: String) = Unit
}