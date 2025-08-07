package io.obrio.challange.ui.features.add_transaction.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddCategoryButton(
    onClick: () -> Unit,
    canAdd: Boolean
) {
    Button(
        onClick = onClick::invoke,
        modifier = Modifier.fillMaxWidth(),
        enabled = canAdd,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF6B35),
            disabledContainerColor = Color.Gray
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            "Add Transaction",
            modifier = Modifier.padding(vertical = 12.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}