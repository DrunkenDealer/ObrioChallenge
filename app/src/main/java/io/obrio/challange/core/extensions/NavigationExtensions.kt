package io.obrio.challange.core.extensions

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

fun <T> NavController.navigateUpWithResult(key: String, result: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, result)
    navigateUp()
}

fun <T> NavBackStackEntry.getOnce(key: String): T? {
    return savedStateHandle.remove<T>(key)
}