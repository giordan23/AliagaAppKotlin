package com.example.aliagaapp.network

class ClienteRepository(private val apiClienteService: ApiClienteService) {
    suspend fun getClienteInfo(dni: String, token: String): ApiClienteResponse? {
        val response = apiClienteService.getClienteInfo(dni, "Bearer $token").execute()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
