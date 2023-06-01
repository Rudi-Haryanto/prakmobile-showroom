package com.unpas.tubes.ui.motor

import java.util.Date

data class MotorData(
    val id: Int,
    val model: String,
    val warna: String,
    val kapasitas: Int,
    val tanggal_rilis: Date,
    val harga: Int
)