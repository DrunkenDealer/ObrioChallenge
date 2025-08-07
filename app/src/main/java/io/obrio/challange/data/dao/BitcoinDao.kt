package io.obrio.challange.data.dao

import androidx.room.*
import io.obrio.challange.data.entity.BitcoinBalanceEntity
import io.obrio.challange.data.entity.BitcoinTransactionEntity

@Dao
interface BitcoinDao {

    // Balance
    @Query("SELECT * FROM bitcoin_balance LIMIT 1")
    suspend fun getBalance(): BitcoinBalanceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBalance(balance: BitcoinBalanceEntity)

    // Transactions
    @Insert
    suspend fun insertTransaction(transaction: BitcoinTransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY timestamp DESC LIMIT :limit OFFSET :offset")
    suspend fun getTransactionsPaged(limit: Int, offset: Int): List<BitcoinTransactionEntity>

    @Query("DELETE FROM transactions")
    suspend fun clearTransactions()
}