package com.danp.imcapp.data

import java.io.Serializable
data class IMCResult(
    val imc: Float,
    val clasificacion: String,
    val descripcion: String
) : Serializable