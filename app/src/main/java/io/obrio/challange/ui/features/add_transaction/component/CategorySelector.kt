package io.obrio.challange.ui.features.add_transaction.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.obrio.challange.ui.features.balance.enums.TransactionCategory

@Composable
fun CategorySelector(
    categories: List<TransactionCategory>,
    selectedCategory: TransactionCategory,
    onSelect: (TransactionCategory) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Category",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            for (i in categories.indices step 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CategoryButton(
                        category = categories[i],
                        isSelected = selectedCategory == categories[i],
                        onSelect = {
                            onSelect.invoke(categories[i])
                        },
                        modifier = Modifier.weight(1f)
                    )

                    if (i + 1 < categories.size) {
                        CategoryButton(
                            category = categories[i + 1],
                            isSelected = selectedCategory == categories[i + 1],
                            onSelect = {
                                onSelect.invoke(categories[i + 1])
                            },
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                if (i < categories.size - 2) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}