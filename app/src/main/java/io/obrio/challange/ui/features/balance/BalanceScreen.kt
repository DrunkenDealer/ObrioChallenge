package io.obrio.challange.ui.features.balance

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.obrio.challange.core.navigation.Navigator
import io.obrio.challange.ui.features.balance.mvi.BalanceIntent
import io.obrio.challange.ui.features.balance.mvi.BalanceState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.obrio.challange.ui.features.balance.component.BitcoinBalanceCard
import io.obrio.challange.ui.features.balance.component.BitcoinPriceCard
import io.obrio.challange.ui.features.balance.component.TopUpDialog
import io.obrio.challange.ui.features.balance.component.TransactionsList

@Preview
@Composable
fun BalanceScreenPreview() {
    BalanceScreen(
        state = BalanceState(),
        intent = BalanceIntent.Empty,
        navigator = Navigator.Empty
    )
}

@Composable
fun BalanceScreen(
    state: BalanceState,
    intent: BalanceIntent,
    navigator: Navigator
) {
    var showTopUpDialog by remember { mutableStateOf(false) }
    var topUpAmount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF8A50),
                        Color(0xFFFF6B35)
                    )
                )
            )
    ) {

        BitcoinPriceCard(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(16.dp),
            bitcoinRate = state.bitcoinRate
        )

        BitcoinBalanceCard(
            modifier =  Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            bitcoinsAmount = state.bitcoinsAmount,
            usdBalance = state.usdBalance
        ) { boolean ->
            showTopUpDialog = boolean
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Add Transaction Button
                Button(
                    onClick = navigator::navToAddTransaction,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6B35)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Add Transaction",
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (state.transactions.isNotEmpty()) {
                    Text(
                        text = "Recent Transactions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Transactions List
                TransactionsList(
                    transactions = state.transactions
                )
            }
        }
    }

    if (showTopUpDialog) {
        TopUpDialog(
            topUpAmount = topUpAmount,
            onValueChange = {
                topUpAmount = it
            },
            onTopUp = { amount ->
                intent.updateBalance(amount)
                showTopUpDialog = false
                topUpAmount = ""
            },
            onDismiss = {
                showTopUpDialog = false
                topUpAmount = ""
            }
        )
    }
}
