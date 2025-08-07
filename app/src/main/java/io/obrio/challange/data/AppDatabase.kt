package io.obrio.challange.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.obrio.challange.data.converters.BigDecimalTypeConverter
import io.obrio.challange.data.dao.BitcoinDao
import io.obrio.challange.data.entity.BitcoinBalanceEntity
import io.obrio.challange.data.entity.BitcoinTransactionEntity

@Database(
    entities = [BitcoinBalanceEntity::class, BitcoinTransactionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BigDecimalTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bitcoinDao(): BitcoinDao
}