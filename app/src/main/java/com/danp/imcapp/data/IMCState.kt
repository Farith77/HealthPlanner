package com.danp.imcapp.data

data class IMCState(
    val sexo: Sexo = Sexo.HOMBRE,
    val peso: Int = 70,
    val altura: Int = 170,
    val edad: Int = 25
)