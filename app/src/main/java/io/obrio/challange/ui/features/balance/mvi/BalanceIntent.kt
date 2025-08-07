package io.obrio.challange.ui.features.balance.mvi

import androidx.compose.runtime.Stable

@Stable
interface BalanceIntent {

    fun addBalance()
    fun addTransaction()

    companion object {
        val Empty: BalanceIntent = EmptyBalanceIntent
    }
}

private object EmptyBalanceIntent : BalanceIntent {
    override fun addBalance() = Unit
    override fun addTransaction() = Unit
}