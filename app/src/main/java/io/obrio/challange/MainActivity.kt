package io.obrio.challange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.obrio.challange.ui.features.balance.navigation.Balance
import io.obrio.challange.ui.navigation.MainNavHost
import io.obrio.challange.ui.navigation.rememberNavigator
import io.obrio.challange.ui.theme.ChallangeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val navigator = rememberNavigator(navController)

            ChallangeTheme {
                MainNavHost(
                    navController = navController,
                    navigator = navigator,
                    startScreen = Balance
                )
            }
        }
    }
}