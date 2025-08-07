package io.obrio.challange.ui.features.balance.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.obrio.challange.core.navigation.Navigator
import io.obrio.challange.ui.features.balance.BalanceScreen
import io.obrio.challange.ui.features.balance.BalanceViewModel
import io.obrio.challange.ui.features.balance.mvi.BalanceEffect
import kotlinx.serialization.Serializable

fun NavGraphBuilder.balanceScreen(
    navigator: Navigator
) {
    composable<Balance> {
        val viewModel = hiltViewModel<BalanceViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is BalanceEffect.ShowToast -> {
                        Toast.makeText(context, effect.text, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        BalanceScreen(
            state = state.value,
            intent = viewModel,
            navigator = navigator
        )
    }
}

@Serializable
data object Balance