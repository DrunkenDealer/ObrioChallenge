package io.obrio.challange.ui.features.balance.manager

import android.content.SharedPreferences
import javax.inject.Inject

class BitcoinRateCacheManager @Inject constructor(
    private val sharedPrefs: SharedPreferences
) {
    companion object {
        private const val KEY_LAST_FETCH = "last_bitcoin_fetch_time"
        private const val ONE_HOUR_MILLIS = 60 * 60 * 1000
        private const val LAST_BITCOIN_RATE = ""
    }

    fun getLastRate(): String? {
        val rate = sharedPrefs.getString(LAST_BITCOIN_RATE, null)
        return rate
    }

    fun shouldFetch(): Boolean {
        val lastFetch = sharedPrefs.getLong(KEY_LAST_FETCH, 0L)
        val now = System.currentTimeMillis()
        return now - lastFetch > ONE_HOUR_MILLIS
    }

    fun updateFetchTimestampAndRate(rate: String) {
        sharedPrefs.edit()
            .putLong(KEY_LAST_FETCH, System.currentTimeMillis())
            .putString(LAST_BITCOIN_RATE, rate)
            .apply()
    }
}
