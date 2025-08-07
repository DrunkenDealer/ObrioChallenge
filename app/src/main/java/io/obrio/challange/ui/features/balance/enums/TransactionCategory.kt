package io.obrio.challange.ui.features.balance.enums

enum class TransactionCategory(val displayName: String, val emoji: String) {
    GROCERIES("Groceries", "🛒"),
    TAXI("Taxi", "🚕"),
    ELECTRONICS("Electronics", "📱"),
    RESTAURANT("Restaurant", "🍽️"),
    OTHER("Other", "💳"),
    TOPUP("Top Up", "💰");

    companion object {
        fun parse(name: String): TransactionCategory {
            return entries.first { it.name == name }
        }
    }
}