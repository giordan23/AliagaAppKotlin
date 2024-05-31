package com.example.aliagaapp.network

data class ApiClienteData(
    val numero: String,
    val nombre_completo: String,
    val nombres: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val codigo_verificacion: String
)

data class ApiClienteResponse(
    val data: ApiClienteData
)
