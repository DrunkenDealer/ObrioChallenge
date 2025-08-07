package io.obrio.challange.ui.features.add_transaction.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.obrio.challange.ui.features.balance.enums.TransactionCategory

@Composable
fun CategoryButton(
    category: TransactionCategory,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onSelect,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFFFE4B5) else Color(0xFFF5F5F5)
        ),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFFF6B35))
        } else null,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = category.emoji,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.displayName,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) Color(0xFFFF6B35) else Color.Black,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}