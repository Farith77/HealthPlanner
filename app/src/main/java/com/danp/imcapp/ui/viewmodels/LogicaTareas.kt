package com.danp.imcapp.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.danp.imcapp.data.TareaCategoria
import com.danp.imcapp.data.Tarea
import com.danp.imcapp.data.TareasGenerator
import com.danp.imcapp.data.TareasEstadisticas

class LogicaTareas(clasificacion: String) {

    // --- ESTADO PRINCIPAL ---
    var tareas by mutableStateOf(TareasGenerator.generarTareas(clasificacion))
        private set

    var categoriaFiltro by mutableStateOf<TareaCategoria?>(null)
        private set

    var nuevoTitulo by mutableStateOf("")
        private set

    var nuevaCategoria by mutableStateOf(TareaCategoria.Habitos)
        private set

    // --- DERIVED STATE ---
    // Se recalcula solo cuando cambia `tareas` o `categoriaFiltro`
    val tareasFiltradas by derivedStateOf {
        if (categoriaFiltro == null) tareas
        else tareas.filter { it.categoria == categoriaFiltro }
    }

    // Se recalcula solo cuando cambia `tareas`
    val estadisticas by derivedStateOf {
        TareasEstadisticas.calcular(tareas)
    }

    // --- ACCIONES ---
    fun toggleCompletada(id: Int) {
        tareas = tareas.map { tarea ->
            if (tarea.id == id) tarea.copy(completada = !tarea.completada)
            else tarea
        }
    }

    fun eliminarTarea(id: Int) {
        tareas = tareas.filter { it.id != id }
    }

    fun agregarTarea() {
        if (nuevoTitulo.isBlank()) return

        val nuevaId = (tareas.maxOfOrNull { it.id } ?: 0) + 1

        tareas = tareas + Tarea(
            id          = nuevaId,
            titulo      = nuevoTitulo.trim(),
            descripcion = "",
            categoria   = nuevaCategoria
        )

        nuevoTitulo    = ""
        nuevaCategoria = TareaCategoria.Habitos
    }

    fun cambiarFiltro(categoria: TareaCategoria?) {
        categoriaFiltro = categoria
    }

    fun cambiarNuevoTitulo(titulo: String) {
        nuevoTitulo = titulo
    }

    fun cambiarNuevaCategoria(categoria: TareaCategoria) {
        nuevaCategoria = categoria
    }
}