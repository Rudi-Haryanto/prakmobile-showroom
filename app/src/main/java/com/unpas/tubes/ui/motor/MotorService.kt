package com.unpas.tubes.ui.motor

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MotorService {
    @POST("sepeda-motor")
    fun sendMotorData(@Body motor: MotorData): Call<Void>
}