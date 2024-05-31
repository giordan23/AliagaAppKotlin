package com.example.aliagaapp.model

data class ProductoEnCompra(
    val producto: Producto,
    val cantidad: Double,
    val unidadMedida: UnidadMedida,
    val secado: Secado,
    val descuentoKg: Double,
) {
    val montoAPagar: Double
        get() = (cantidad * producto.precio) - (descuentoKg * producto.precio)

    enum class Secado(val value: Int) {
        HUMEDO(1),
        ESTANDAR(2),
        SECO(3)
    }

    enum class UnidadMedida(val value: Int) {
        KILOGRAMO(1),
        LATA(2),
        BALDE(3)
    }
}

//