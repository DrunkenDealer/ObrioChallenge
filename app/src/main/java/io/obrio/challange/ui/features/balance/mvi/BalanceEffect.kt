package io.obrio.challange.ui.features.balance.mvi

sealed class BalanceEffect {
    data class ShowToast(val text: String): BalanceEffect()
}