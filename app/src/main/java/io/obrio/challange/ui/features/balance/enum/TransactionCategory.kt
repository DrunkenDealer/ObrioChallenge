package io.obrio.challange.ui.features.balance.enum

enum class TransactionCategory(val displayName: String, val emoji: String) {
    GROCERIES("Groceries", "🛒"),
    TAXI("Taxi", "🚕"),
    ELECTRONICS("Electronics", "📱"),
    RESTAURANT("Restaurant", "🍽️"),
    OTHER("Other", "💳"),
    TOPUP("Top Up", "💰")
}