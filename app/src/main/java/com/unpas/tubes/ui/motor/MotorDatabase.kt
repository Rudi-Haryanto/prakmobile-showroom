package com.unpas.tubes.ui.motor

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MotorEntity::class], version = 1, exportSchema = false)
abstract class MotorDatabase : RoomDatabase() {
    abstract fun motorDao(): MotorDao

    companion object {
        @Volatile
        private var INSTANCE: MotorDatabase? = null

        fun getDatabase(context: Context): MotorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MotorDatabase::class.java,
                    "motor_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}