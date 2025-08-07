package io.obrio.challange.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import io.obrio.challange.core.extensions.navigateUpWithResult
import io.obrio.challange.core.navigation.Navigator
import io.obrio.challange.ui.features.add_transaction.navigation.AddTransaction

class MainNavigator(
    val navController: NavHostController
) : Navigator {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun <T> navigateUpWithResult(key: String, result: T) {
         navController.navigateUpWithResult(key, result)
    }

    override fun navToAddTransaction(navOptions: NavOptions?) {
        navController.navigate(AddTransaction, navOptions)
    }
}

@Composable
fun rememberNavigator(
    navController: NavHostController
): Navigator {
    return remember {
        MainNavigator(
            navController = navController
        )
    }
}