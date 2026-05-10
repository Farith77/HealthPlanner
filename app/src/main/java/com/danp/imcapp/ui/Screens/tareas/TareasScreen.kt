package com.danp.imcapp.ui.Screens.tareas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danp.imcapp.ui.viewmodels.LogicaTareas

@Composable
fun TareasScreen(
    navController: NavController,
    clasificacion: String
) {
    val primaryColor     = Color(0xFF7C3AED)
    val backgroundColor  = Color(0xFFF3EEF8)

    // LogicaTareas se recrea solo si cambia la clasificación
    val logica = remember(key1 = clasificacion) {
        LogicaTareas(clasificacion)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // --- HEADER ---
            TareasHeader(
                clasificacion = clasificacion,
                onVolver      = { navController.popBackStack() }
            )

            // --- ESTADÍSTICAS ---
            EstadisticasCard(
                estadisticas = logica.estadisticas,
                primaryColor = primaryColor
            )

            // --- FILTROS DE CATEGORÍA ---
            FiltrosCategorias(
                filtroActivo = logica.categoriaFiltro,
                primaryColor = primaryColor,
                onFiltroClick = { logica.cambiarFiltro(it) }
            )

            // --- AGREGAR TAREA ---
            AgregarTareaCard(
                titulo        = logica.nuevoTitulo,
                categoria     = logica.nuevaCategoria,
                primaryColor  = primaryColor,
                onTituloChange    = { logica.cambiarNuevoTitulo(it) },
                onCategoriaChange = { logica.cambiarNuevaCategoria(it) },
                onAgregar         = { logica.agregarTarea() }
            )

            // --- LISTA DE TAREAS ---
            ListaTareas(
                tareas       = logica.tareasFiltradas,
                primaryColor = primaryColor,
                onToggle     = { logica.toggleCompletada(it) },
                onEliminar   = { logica.eliminarTarea(it) }
            )
        }
    }
}