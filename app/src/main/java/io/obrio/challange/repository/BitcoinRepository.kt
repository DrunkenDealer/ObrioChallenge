package io.obrio.challange.repository

import io.obrio.challange.data.dao.BitcoinDao
import io.obrio.challange.data.entity.BitcoinBalanceEntity
import io.obrio.challange.data.entity.BitcoinTransactionEntity
import java.math.BigDecimal
import javax.inject.Inject

class BitcoinRepository @Inject constructor(
    private val dao: BitcoinDao
) {

    suspend fun getBalance(): BigDecimal {
        return dao.getBalance()?.balance ?: BigDecimal.ZERO
    }

    suspend fun updateBalanceAndReturn(amount: BigDecimal): BigDecimal {
        val current = getBalance()
        val newBalance = current + amount
        dao.saveBalance(BitcoinBalanceEntity(balance = newBalance))
        return newBalance
    }

    suspend fun addTransaction(amount: BigDecimal, category: String) {
        val timestamp = System.currentTimeMillis()
        dao.insertTransaction(BitcoinTransactionEntity(timestamp = timestamp, amount = amount, category = category))
    }

    suspend fun getPagedTransactions(page: Int, pageSize: Int = 20): List<BitcoinTransactionEntity> {
        return dao.getTransactionsPaged(limit = pageSize, offset = page * pageSize)
    }
}