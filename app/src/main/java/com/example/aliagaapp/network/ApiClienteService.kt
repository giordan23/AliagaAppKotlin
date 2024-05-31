package com.example.aliagaapp.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClienteService {
    @POST("dni")
    fun getClienteInfo(
        @Query("dni") dni: String,
        @Header("Authorization") token: String
    ): Call<ApiClienteResponse>
}
