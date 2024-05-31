package com.example.aliagaapp.model

data class Producto(
    val nombreProducto: NombreProducto,
    var precio: Double,
)
{
    enum class NombreProducto(val value: Int){
        CAFE(1),
        CACAO(2),
        MAIZ(3),
        ACHIOTE(4),
        OTROS(5)
    }
}
