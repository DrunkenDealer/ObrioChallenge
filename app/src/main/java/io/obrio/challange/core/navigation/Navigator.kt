package io.obrio.challange.core.navigation

import androidx.compose.runtime.Stable
import androidx.navigation.NavOptions

@Stable
interface Navigator {

    fun navigateUp()
    fun <T>navigateUpWithResult(key: String, result: T)
    fun navToAddTransaction(navOptions: NavOptions? = null)
    fun navToAddBalance(navOptions: NavOptions? = null)

    companion object {
        val Empty: Navigator = EmptyNavigator()
    }
}

private class EmptyNavigator: Navigator {
    override fun navigateUp() = Unit
    override fun <T> navigateUpWithResult(key: String, result: T)= Unit
    override fun navToAddTransaction(navOptions: NavOptions?)= Unit
    override fun navToAddBalance(navOptions: NavOptions?)= Unit
}