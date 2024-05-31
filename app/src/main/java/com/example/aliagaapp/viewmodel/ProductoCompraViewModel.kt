package com.example.aliagaapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.aliagaapp.model.Producto
import com.example.aliagaapp.model.ProductoEnCompra

class ProductoCompraViewModel : ViewModel() {

    private val _productos = MutableStateFlow<List<ProductoEnCompra>>(emptyList())
    val productos: StateFlow<List<ProductoEnCompra>> get() = _productos

    private val _productosDisponibles = MutableStateFlow<List<Producto>>(listOf(
        Producto(Producto.NombreProducto.CAFE, 10.0),
        Producto(Producto.NombreProducto.TEA, 5.0)
    ))
    val productosDisponibles: StateFlow<List<Producto>> get() = _productosDisponibles

    fun agregarProducto(producto: ProductoEnCompra) {
        _productos.value = _productos.value + producto
    }

    fun actualizarProducto(producto: ProductoEnCompra) {
        _productos.value = _productos.value.map {
            if (it.producto == producto.producto) producto else it
        }
    }

    fun eliminarProducto(producto: ProductoEnCompra) {
        _productos.value = _productos.value - producto
    }
}
