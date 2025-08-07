package io.obrio.challange.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "bitcoin_balance")
data class BitcoinBalanceEntity(
    @PrimaryKey val id: String = "singleton",
    val balance: BigDecimal
)