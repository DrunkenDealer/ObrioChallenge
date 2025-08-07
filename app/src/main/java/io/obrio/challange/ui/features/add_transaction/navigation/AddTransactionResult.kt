package io.obrio.challange.ui.features.add_transaction.navigation

import kotlinx.serialization.Serializable

@Serializable
object AddTransactionResult: java.io.Serializable {
    private fun readResolve(): Any = AddTransactionResult
}