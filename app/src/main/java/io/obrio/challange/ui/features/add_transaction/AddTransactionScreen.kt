package io.obrio.challange.ui.features.add_transaction

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.obrio.challange.core.navigation.Navigator
import io.obrio.challange.ui.features.add_transaction.mvi.AddTransactionIntent
import io.obrio.challange.ui.features.add_transaction.mvi.AddTransactionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.obrio.challange.ui.features.add_transaction.component.AddCategoryButton
import io.obrio.challange.ui.features.add_transaction.component.CategorySelector
import io.obrio.challange.ui.features.add_transaction.component.EnterAmountCard
import io.obrio.challange.ui.features.add_transaction.navigation.ADD_TRANSACTION_NAV_KEY
import io.obrio.challange.ui.features.add_transaction.navigation.AddTransactionResult
import io.obrio.challange.ui.features.balance.enums.TransactionCategory


@Preview
@Composable
fun AddTransactionScreenPreview() {
    AddTransactionScreen(
        state = AddTransactionState(),
        intent = AddTransactionIntent.Empty,
        navigator = Navigator.Empty
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    state: AddTransactionState,
    intent: AddTransactionIntent,
    navigator: Navigator
) {
    var selectedCategory by remember { mutableStateOf(TransactionCategory.GROCERIES) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {

        TopAppBar(
            title = { Text("Add Transaction") },
            navigationIcon = {
                IconButton(onClick = navigator::navigateUp) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFFF6B35),
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            EnterAmountCard(
                amount = state.enteredAmount,
                usdBalance = state.usdBalance
            ) {
                intent.onAmountChange(it)
            }

            Spacer(modifier = Modifier.height(20.dp))

            CategorySelector(
                categories = state.categories,
                selectedCategory = selectedCategory,
                onSelect = {
                    selectedCategory = it
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AddCategoryButton(
                canAdd = state.canAddTransaction,
                onClick = {
                    intent.addTransaction(state.enteredAmount.toDouble(), selectedCategory)
                    navigator.navigateUpWithResult(
                        key = ADD_TRANSACTION_NAV_KEY,
                        result = AddTransactionResult
                    )
                }
            )

            if (!state.canAddTransaction && state.enteredAmount.isNotEmpty()) {
                Text(
                    text = "Insufficient balance. Available: ₿ ${state.bitcoinsAmount}",
                    color = Color.Red,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}