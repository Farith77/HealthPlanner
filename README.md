# 📊 IMC App

Aplicación móvil Android para calcular el **Índice de Masa Corporal (IMC)**, visualizar resultados y gestionar tareas de salud personalizadas según el resultado obtenido.

---

## 📱 Pantallas

### 🏠 Pantalla de Inicio
- Selección de sexo (Hombre / Mujer)
- Ajuste de altura mediante slider (100 cm – 220 cm)
- Ajuste de peso y edad con botones `+` / `−`
- Botón **CALCULAR** que navega a la pantalla de resultados

### 📋 Pantalla de Resultados
- Muestra el valor IMC calculado
- Clasificación del resultado (Bajo peso, Normal, Sobrepeso, Obesidad)
- Descripción del estado de salud
- Botón **Volver** para regresar al inicio
- Botón **TAREAS** para ir al plan de tareas personalizado

### ✅ Pantalla de Tareas
- Lista To-Do con tareas sugeridas según la clasificación IMC
- Marcar tareas como completadas
- Agregar nuevas tareas con título y categoría
- Eliminar tareas existentes
- Filtrar tareas por categoría
- Estadísticas de progreso en tiempo real

---

## 🗂️ Estructura del Proyecto

```
app/src/main/java/com/danp/imcapp/
│
├── data/
│   ├── IMCResult.kt             # Resultado del cálculo IMC
│   ├── IMCState.kt              # Estado de la pantalla de inicio
│   ├── IMCCalculator.kt         # Lógica de cálculo del IMC
│   ├── IMCValidator.kt          # Validaciones de peso, altura y edad
│   ├── Sexo.kt                  # Enum de sexo (HOMBRE / MUJER)
│   ├── Tarea.kt                 # Entidad de tarea
│   ├── TareaCategoria.kt        # Enum de categorías de tareas
│   ├── TareasState.kt           # Estado de la pantalla de tareas
│   ├── TareasEstadisticas.kt    # Cálculo de estadísticas
│   └── TareasGenerator.kt       # Generador de tareas según IMC
│
├── ui/
│   ├── navigation/
│   │   └── AppNavigation.kt     # Configuración de rutas
│   │
│   ├── screens/
│   │   ├── inicio/
│   │   │   └── InicioScreen.kt
│   │   │
│   │   ├── resultado/
│   │   │   └── ResultadoScreen.kt
│   │   │
│   │   └── tareas/
│   │       ├── TareasScreen.kt       # Orquestador principal
│   │       ├── TareasHeader.kt
│   │       ├── EstadisticasCard.kt
│   │       ├── FiltrosCategorias.kt
│   │       ├── AgregarTareaCard.kt
│   │       └── ListaTareas.kt
│   │
│   └── viewmodels/
│       ├── InicioVM.kt          # ViewModel de la pantalla de inicio
│       ├── ResultadosVM.kt      # ViewModel de la pantalla de resultados
│       └── LogicaTareas.kt      # Lógica de estado para tareas (sin ViewModel)
|__ MainActivity.kt

---

## 🧮 Clasificación IMC

| IMC | Clasificación |
|-----|--------------|
| < 18.5 | Bajo peso |
| 18.5 – 24.9 | Normal |
| 25.0 – 29.9 | Sobrepeso |
| ≥ 30.0 | Obesidad |

---

## 🗃️ Categorías de Tareas

| Categoría |
|-----------|
| Ejercicio |
| Nutrición |
| Descanso |
| Hidratación |
| Hábitos |

---

## ⚙️ Tecnologías Utilizadas

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose
- **Navegación:** Navigation Compose
- **Estado:** `mutableStateOf`, `derivedStateOf`, `remember`
- **Arquitectura:** MVVM en Inicio y Resultado / Estado puro con `LogicaTareas` en Tareas
- **Min SDK:** 24
- **Target SDK:** 35

---

## 🔑 Conceptos Clave de Implementación

### Paso de datos entre pantallas
Los resultados del IMC se pasan entre pantallas usando `savedStateHandle`:

```kotlin
// En InicioScreen — guardar antes de navegar
navController.currentBackStackEntry
    ?.savedStateHandle
    ?.set("resultado", viewModel.calcularResultado())

navController.navigate("resultado")

// En ResultadoScreen — leer al llegar
val resultado = navController
    .previousBackStackEntry
    ?.savedStateHandle
    ?.get<IMCResult>("resultado")
```

### Estado reactivo con derivedStateOf
`LogicaTareas` usa `derivedStateOf` para que las tareas filtradas y las estadísticas solo se recalculen cuando sus dependencias cambian realmente:

```kotlin
val tareasFiltradas by derivedStateOf {
    if (categoriaFiltro == null) tareas
    else tareas.filter { it.categoria == categoriaFiltro }
}

val estadisticas by derivedStateOf {
    TareasEstadisticas.calcular(tareas)
}
```

### Recreación de LogicaTareas con key
Si el usuario vuelve con una clasificación diferente, `LogicaTareas` se recrea automáticamente:

```kotlin
val logica = remember(key1 = clasificacion) {
    LogicaTareas(clasificacion)
}
```

---

## 🚀 Cómo Ejecutar

1. Clonar el repositorio
2. Abrir con **Android Studio**
3. Sincronizar dependencias con Gradle
4. Ejecutar en emulador o dispositivo físico (API 24+)

---

## 👨‍💻 Autor

Desarrollado por Alan Jorge Garcia Apaza como proyecto para el curso **DANP** — Desarrollo de Avanzado de Nuevas Plataformas.
