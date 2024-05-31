package com.example.aliagaapp.components.compras

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aliagaapp.components.productos.AgregarProductoModal
import com.example.aliagaapp.model.Producto
import com.example.aliagaapp.model.ProductoEnCompra
import com.example.aliagaapp.viewmodel.ProductoCompraViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosCompraPage(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val viewModel: ProductoCompraViewModel = viewModel()
    val productos by viewModel.productos.collectAsState()
    val productosDisponibles = listOf(
        // Aquí se deben agregar los productos disponibles. Esto puede venir del ViewModel u otro lugar
        Producto(Producto.NombreProducto.CAFE, 10.0),
        Producto(Producto.NombreProducto.CACAO, 5.0)
    )
    var showModal by remember { mutableStateOf(false) }
    var productoSeleccionado by remember { mutableStateOf<ProductoEnCompra?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showModal = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Producto")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(productos) { producto ->
                    ProductoEnCompraItem(
                        productoEnCompra = producto,
                        onEdit = {
                            productoSeleccionado = it
                            showModal = true
                        },
                        onDelete = {
                            viewModel.eliminarProducto(it)
                        }
                    )
                }
            }
        }
    }

    if (showModal) {
        AgregarProductoModal(
            productoEnCompra = productoSeleccionado,
            onDismiss = { showModal = false },
            onSave = { producto ->
                if (productoSeleccionado == null) {
                    viewModel.agregarProducto(producto)
                } else {
                    viewModel.actualizarProducto(producto)
                }
                showModal = false
            },
            productosDisponibles = productosDisponibles
        )
    }
}

@Composable
fun ProductoEnCompraItem(
    productoEnCompra: ProductoEnCompra,
    onEdit: (ProductoEnCompra) -> Unit,
    onDelete: (ProductoEnCompra) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onEdit(productoEnCompra) }
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = productoEnCompra.producto.nombreProducto.name)
            Text(text = "Cantidad: ${productoEnCompra.cantidad}")
            Text(text = "Precio: S/. ${productoEnCompra.producto.precio}")
        }
        IconButton(onClick = { onDelete(productoEnCompra) }) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar Producto")
        }
    }
}
