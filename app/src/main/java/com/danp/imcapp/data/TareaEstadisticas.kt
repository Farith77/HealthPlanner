package com.danp.imcapp.data

data class TareasEstadisticas(
    val total: Int,
    val completadas: Int,
    val pendientes: Int,
    val porcentajeCumplimiento: Float
) {
    companion object {
        fun calcular(tareas: List<Tarea>): TareasEstadisticas {
            val total       = tareas.size
            val completadas = tareas.count { it.completada }
            val pendientes  = total - completadas
            val porcentaje  = if (total > 0) (completadas.toFloat() / total) * 100f else 0f

            return TareasEstadisticas(
                total                  = total,
                completadas            = completadas,
                pendientes             = pendientes,
                porcentajeCumplimiento = porcentaje
            )
        }
    }
}