package io.obrio.challange.ui.features.balance.mvi

import androidx.compose.runtime.Stable

@Stable
interface BalanceIntent {

    fun updateBalance(amount: Double)
    fun loadMore()

    companion object {
        val Empty: BalanceIntent = EmptyBalanceIntent
    }
}

private object EmptyBalanceIntent : BalanceIntent {
    override fun updateBalance(amount: Double) = Unit
    override fun loadMore() = Unit
}