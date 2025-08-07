package io.obrio.challange.ui.features.add_transaction.mvi

sealed class AddTransactionEffect {
    data class ShowToast(val text: String): AddTransactionEffect()
}