package com.danp.imcapp.data

data class TareaState(
    val tarea: List<Tarea> = emptyList(),
    val CategoriaFiltros: TareaCategoria? = null,
    val nuevoTituloTarea: String = "",
    val nuevaCategoria: TareaCategoria = TareaCategoria.Habitos
)