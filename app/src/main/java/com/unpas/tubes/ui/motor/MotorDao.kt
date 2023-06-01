package com.unpas.tubes.ui.motor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete

@Dao
interface MotorDao {
    @Query("SELECT * FROM motor_table")
    suspend fun getAllMotors(): LiveData<List<MotorEntity>>

    @Insert
    suspend fun insertMotor(motor: MotorEntity)

    @Update
    suspend fun updateMotor(motor: MotorEntity)

    @Delete
    suspend fun deleteMotor(motor: MotorEntity)
}