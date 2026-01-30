package com.example.actividadfisicaapp.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * Modelo de datos para representar una sesión de actividad física
 * @param nombre Nombre de la actividad (ej: Correr, Yoga, etc.)
 * @param duracion Duración en minutos
 * @param fechaHora Fecha y hora de registro de la actividad
 */
data class ActivitySession(
    val nombre: String,
    val duracion: Int,
    val fechaHora: Date = Date()
) {
    /**
     * Obtiene la fecha formateada como String
     * @return Fecha en formato "dd/MM/yyyy HH:mm"
     */
    fun getFechaFormateada(): String {
        val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return formato.format(fechaHora)
    }

    /**
     * Determina el tipo de actividad según el nombre
     * @return Tipo de actividad (Cardio, Flexibilidad, Fuerza, Otro)
     */
    fun getTipoActividad(): String {
        return when (nombre.lowercase()) {
            "correr", "caminar", "nadar", "ciclismo", "running" -> "Cardio"
            "yoga", "pilates", "estiramientos", "stretching" -> "Flexibilidad"
            "gimnasio", "pesas", "musculación", "gym" -> "Fuerza"
            else -> "Otro"
        }
    }
}
