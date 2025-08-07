package io.obrio.challange.ui.features.balance.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.obrio.challange.ui.features.balance.data.Transaction
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TransactionsList(transactions: List<Transaction>) {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    val groupedTransactions = transactions
        .sortedByDescending { it.timestamp }
        .groupBy { dateFormatter.format(Date(it.timestamp)) }

    LazyColumn {
        groupedTransactions.forEach { (date, dayTransactions) ->
            item {
                val displayDate = when {
                    date == dateFormatter.format(Date()) -> "Today"
                    date == dateFormatter.format(Date(System.currentTimeMillis() - 86400000)) -> "Yesterday"
                    else -> SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateFormatter.parse(date))
                }

                Text(
                    text = displayDate,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(dayTransactions) { transaction ->
                TransactionItem(
                    transaction = transaction,
                    time = timeFormatter.format(Date(transaction.timestamp))
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}