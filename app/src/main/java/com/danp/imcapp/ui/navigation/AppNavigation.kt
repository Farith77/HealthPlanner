package com.danp.imcapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.danp.imcapp.ui.Screens.InicioScreen
import com.danp.imcapp.ui.Screens.ResultadoScreen
import androidx.navigation.compose.composable
import com.danp.imcapp.ui.Screens.tareas.TareasScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            InicioScreen(navController)
        }
        composable("resultado") {
            ResultadoScreen(navController)
        }
        composable("tareas/{clasificacion}") { backStackEntry ->
            val clasificacion = backStackEntry.arguments?.getString("clasificacion") ?: ""
            TareasScreen(navController, clasificacion)
        }
    }
}