package com.example.actividadfisicaapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actividadfisicaapp.R
import com.example.actividadfisicaapp.model.ActivitySession

/**
 * ViewHolder para los ítems del RecyclerView
 * Gestiona las vistas de cada sesión de actividad
 */
class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    
    // Referencias a las vistas del layout
    private val tvNombre: TextView = itemView.findViewById(R.id.tvNombreActividad)
    private val tvDuracion: TextView = itemView.findViewById(R.id.tvDuracion)
    private val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
    private val tvTipo: TextView = itemView.findViewById(R.id.tvTipo)

    /**
     * Vincula los datos de una sesión con las vistas
     * @param session Sesión de actividad a mostrar
     */
    fun bind(session: ActivitySession) {
        tvNombre.text = session.nombre
        tvDuracion.text = "${session.duracion} min"
        tvFecha.text = session.getFechaFormateada()
        tvTipo.text = session.getTipoActividad()
    }
}
