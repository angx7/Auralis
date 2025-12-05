package com.example.cameratest.models

data class AiReport(
    val resumen: String,
    val precisionGlobal: Double,
    val tendenciaPrecision: String,
    val graficaPrecision: List<Int>,
    val ritmoEstableNivel: String,
    val dinamicaPuntaje: Double,
    val precisionActual: Int
)