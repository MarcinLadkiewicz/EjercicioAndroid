package com.example.actividadfisicaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.actividadfisicaapp.R
import com.example.actividadfisicaapp.model.ActivitySession

/**
 * Adapter para el RecyclerView de sesiones de actividad
 * Utiliza ListAdapter con DiffUtil para optimizar las actualizaciones
 */
class ActivityAdapter : ListAdapter<ActivitySession, ActivityViewHolder>(ActivityDiffCallback()) {

    /**
     * Crea nuevas instancias de ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity_session, parent, false)
        return ActivityViewHolder(view)
    }

    /**
     * Vincula los datos con el ViewHolder en una posición específica
     */
    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val session = getItem(position)
        holder.bind(session)
    }

    /**
     * Actualiza la lista de sesiones
     * @param newList Nueva lista de sesiones a mostrar
     */
    fun updateSessions(newList: List<ActivitySession>) {
        submitList(newList.toList())
    }
}

/**
 * Clase que determina si dos items son iguales para optimizar el RecyclerView
 */
class ActivityDiffCallback : DiffUtil.ItemCallback<ActivitySession>() {
    
    /**
     * Verifica si dos items representan el mismo objeto
     */
    override fun areItemsTheSame(oldItem: ActivitySession, newItem: ActivitySession): Boolean {
        return oldItem.fechaHora == newItem.fechaHora
    }

    /**
     * Verifica si el contenido de dos items es el mismo
     */
    override fun areContentsTheSame(oldItem: ActivitySession, newItem: ActivitySession): Boolean {
        return oldItem == newItem
    }
}
