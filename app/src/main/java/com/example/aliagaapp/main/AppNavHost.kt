package com.example.aliagaapp.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.aliagaapp.components.compras.IngresarClientePage
import com.example.aliagaapp.components.compras.ProductosCompraPage
import com.example.aliagaapp.components.precios.ActualizarPrecioPage
import com.example.aliagaapp.components.productos.AgregarProductoModal
import com.example.aliagaapp.viewmodel.ProductoViewModel

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: androidx.navigation.NavHostController) {
    val productViewModel: ProductoViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main", modifier = modifier) {
        composable("main") { MainPage(navController) }
        composable("compras") { IngresarClientePage(navController, productViewModel) }
        composable("productosCompra") { ProductosCompraPage(navController, productViewModel) }
        composable(
            route = "actualizarPrecio/{producto}",
            arguments = listOf(navArgument("producto") { type = NavType.StringType })
        ) { backStackEntry ->
            val producto = backStackEntry.arguments?.getString("producto") ?: ""
            ActualizarPrecioPage(navController, producto, productViewModel)
        }
        composable("agregarProducto") {
            AgregarProductoModal(
                productoEnCompra = null,
                onDismiss = { navController.popBackStack() },
                onSave = { productoEnCompra ->
                    // Manejar la lógica de guardar producto en compra aquí
                    navController.popBackStack()
                },
                productosDisponibles = productViewModel.productos
            )
        }
    }
}
