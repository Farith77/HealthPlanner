package com.danp.imcapp.data

import java.io.Serializable

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val categoria: TareaCategoria,
    val completada: Boolean = false
) : Serializable