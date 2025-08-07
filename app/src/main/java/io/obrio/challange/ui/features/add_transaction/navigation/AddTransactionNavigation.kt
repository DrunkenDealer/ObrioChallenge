package io.obrio.challange.ui.features.add_transaction.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.obrio.challange.core.navigation.Navigator
import io.obrio.challange.ui.features.add_transaction.AddTransactionScreen
import io.obrio.challange.ui.features.add_transaction.AddTransactionViewModel
import io.obrio.challange.ui.features.add_transaction.mvi.AddTransactionEffect
import kotlinx.serialization.Serializable

const val ADD_TRANSACTION_NAV_KEY = "ADD_TRANSACTION_NAV_KEY"

fun NavGraphBuilder.addTransactionScreen(
    navigator: Navigator
) {
    composable<AddTransaction> {
        val viewModel = hiltViewModel<AddTransactionViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is AddTransactionEffect.ShowToast -> {
                        Toast.makeText(context, effect.text, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        AddTransactionScreen(
            state = state.value,
            intent = viewModel,
            navigator = navigator
        )
    }
}

@Serializable
data object AddTransaction