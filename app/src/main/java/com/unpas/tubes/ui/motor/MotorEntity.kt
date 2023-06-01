package com.unpas.tubes.ui.motor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motor_table")
data class MotorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val model: String,
    val warna: String,
    val kapasitas: String,
    val tanggalRilis: String,
    val harga: String
)