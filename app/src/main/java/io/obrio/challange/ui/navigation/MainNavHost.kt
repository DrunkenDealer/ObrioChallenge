package io.obrio.challange.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.obrio.challange.core.navigation.Navigator
import io.obrio.challange.ui.features.add_transaction.navigation.addTransactionScreen
import io.obrio.challange.ui.features.balance.navigation.balanceScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    startScreen: Any,
    navController: NavHostController,
    navigator: Navigator,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startScreen
    ) {
        balanceScreen(navigator)
        addTransactionScreen(navigator)
    }
}