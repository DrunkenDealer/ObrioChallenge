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
import io.obrio.challange.ui.features.balance.data.TransactionGroup

@Composable
fun TransactionsList(
    transactions: List<TransactionGroup>
) {

    LazyColumn {
        transactions.forEach { group ->
            item {
                Text(
                    text = group.dateLabel,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(group.transactions) { transaction ->
                TransactionItem(
                    amount = transaction.amount,
                    category = transaction.category,
                    time = transaction.time
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
