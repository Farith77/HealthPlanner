package com.danp.imcapp.data



object TareasGenerator {

    fun generarTareas(clasificacion: String): List<Tarea> {
        val tareas = tareasBase().toMutableList()
        tareas.addAll(tareasPorClasificacion(clasificacion))
        return tareas.mapIndexed { index, tarea -> tarea.copy(id = index + 1) }
    }

    // Tareas que aplican para cualquier clasificación IMC
    private fun tareasBase(): List<Tarea> {
        return listOf(
            Tarea(
                id          = 0,
                titulo      = "Tomar 8 vasos de agua",
                descripcion = "Mantén tu cuerpo hidratado durante todo el día",
                categoria   = TareaCategoria.Hidratacion
            ),
            Tarea(
                id          = 0,
                titulo      = "Dormir 7-8 horas",
                descripcion = "El descanso adecuado es fundamental para tu salud",
                categoria   = TareaCategoria.Descanso
            ),
            Tarea(
                id          = 0,
                titulo      = "Evitar pantallas antes de dormir",
                descripcion = "Apaga dispositivos al menos 30 minutos antes de acostarte",
                categoria   = TareaCategoria.Habitos
            )
        )
    }

    // Tareas específicas según el resultado del IMC
    private fun tareasPorClasificacion(clasificacion: String): List<Tarea> {
        return when (clasificacion.lowercase()) {

            "bajo peso" -> listOf(
                Tarea(
                    id          = 0,
                    titulo      = "Aumentar calorías diarias",
                    descripcion = "Consulta con un nutricionista para un plan de alimentación",
                    categoria   = TareaCategoria.Nutricion
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Consumir proteínas en cada comida",
                    descripcion = "Incluye huevos, legumbres o carnes magras",
                    categoria   = TareaCategoria.Nutricion
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Ejercicio de fuerza 3 veces por semana",
                    descripcion = "Ayuda a ganar masa muscular de forma saludable",
                    categoria   = TareaCategoria.Ejercicio
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Comer cada 3-4 horas",
                    descripcion = "Establece horarios de comida regulares",
                    categoria   = TareaCategoria.Habitos
                )
            )

            "normal", "peso normal" -> listOf(
                Tarea(
                    id          = 0,
                    titulo      = "30 minutos de cardio diario",
                    descripcion = "Camina, trota o nada para mantener tu condición",
                    categoria   = TareaCategoria.Ejercicio
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Mantener dieta balanceada",
                    descripcion = "Incluye frutas, verduras, proteínas y carbohidratos",
                    categoria   = TareaCategoria.Nutricion
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Revisión médica anual",
                    descripcion = "Monitorea tu salud general con chequeos periódicos",
                    categoria   = TareaCategoria.Habitos
                )
            )

            "sobrepeso" -> listOf(
                Tarea(
                    id          = 0,
                    titulo      = "Caminar 45 minutos al día",
                    descripcion = "Empieza con caminatas y aumenta la intensidad gradualmente",
                    categoria   = TareaCategoria.Ejercicio
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Reducir azúcares y ultraprocesados",
                    descripcion = "Elimina refrescos, snacks y comida rápida",
                    categoria   = TareaCategoria.Nutricion
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Comer en porciones más pequeñas",
                    descripcion = "Usa platos más pequeños y mastica despacio",
                    categoria   = TareaCategoria.Habitos
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Aumentar consumo de vegetales",
                    descripcion = "La mitad de tu plato debe ser verduras",
                    categoria   = TareaCategoria.Nutricion
                )
            )

            "obesidad", "obesidad grado i", "obesidad grado ii", "obesidad grado iii" -> listOf(
                Tarea(
                    id          = 0,
                    titulo      = "Consultar con un médico",
                    descripcion = "Es importante tener supervisión profesional en tu proceso",
                    categoria   = TareaCategoria.Habitos
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Actividad física de bajo impacto",
                    descripcion = "Prueba natación o bicicleta estática para cuidar tus articulaciones",
                    categoria   = TareaCategoria.Ejercicio
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Llevar un diario alimenticio",
                    descripcion = "Registra todo lo que comes para tomar conciencia de tus hábitos",
                    categoria   = TareaCategoria.Nutricion
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Eliminar bebidas azucaradas",
                    descripcion = "Reemplázalas por agua o infusiones sin azúcar",
                    categoria   = TareaCategoria.Nutricion
                ),
                Tarea(
                    id          = 0,
                    titulo      = "Establecer meta semanal de pasos",
                    descripcion = "Usa tu celular para contar pasos, empieza con 5000 diarios",
                    categoria   = TareaCategoria.Ejercicio
                )
            )

            else -> listOf(
                Tarea(
                    id          = 0,
                    titulo      = "Consulta con un especialista",
                    descripcion = "Un profesional te guiará según tu situación particular",
                    categoria   = TareaCategoria.Habitos
                )
            )
        }
    }
}