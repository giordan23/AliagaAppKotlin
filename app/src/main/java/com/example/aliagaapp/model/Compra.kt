package com.example.aliagaapp.model

data class Compra(
    val id: String,
    val cliente: Cliente,
    val productos: List<ProductoEnCompra>
) {
    val montoTotal: Double
        get() = productos.sumOf { it.montoAPagar }
}