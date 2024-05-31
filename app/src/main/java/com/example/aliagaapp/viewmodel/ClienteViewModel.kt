package com.example.aliagaapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aliagaapp.network.ApiCliente
import com.example.aliagaapp.network.ApiClienteService
import com.example.aliagaapp.network.ClienteRepository
import com.example.aliagaapp.network.ApiClienteResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ClienteViewModel : ViewModel() {
    private val apiService: ApiClienteService = ApiCliente.retrofit.create(ApiClienteService::class.java)
    private val clienteRepository = ClienteRepository(apiService)

    var clienteResponse by mutableStateOf<ApiClienteResponse?>(null)
        private set

    var nombreCompleto by mutableStateOf("")
        private set

    fun fetchClienteInfo(dni: String, token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("ClienteViewModel", "Fetching client info for DNI: $dni")
                clienteResponse = clienteRepository.getClienteInfo(dni, token)
                clienteResponse?.data?.let {
                    nombreCompleto = "${it.nombres} ${it.apellido_paterno} ${it.apellido_materno}"
                }
                Log.d("ClienteViewModel", "Client info fetched successfully: $clienteResponse")
            } catch (e: Exception) {
                Log.e("ClienteViewModel", "Error fetching client info", e)
            }
        }
    }
}
