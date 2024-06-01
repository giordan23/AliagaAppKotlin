package com.example.aliagaapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.aliagaapp.model.Producto

class ProductoViewModel : ViewModel() {
    var productos = mutableStateListOf(
        Producto(Producto.NombreProducto.CAFE, 0.00),
        Producto(Producto.NombreProducto.CACAO, 0.00),
        Producto(Producto.NombreProducto.MAIZ, 0.00),
        Producto(Producto.NombreProducto.ACHIOTE, 0.00),
        Producto(Producto.NombreProducto.OTROS, 0.00)
    )
    fun actualizarPrecio(nombreProducto: Producto.NombreProducto, nuevoPrecio: Double) {
        val index = productos.indexOfFirst { it.nombreProducto == nombreProducto }
        if (index != -1) {
            productos[index] = productos[index].copy(precio = nuevoPrecio)
        }
    }
}
