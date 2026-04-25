package com.saravazquez.footballapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saravazquez.footballapp.R
import com.saravazquez.footballapp.models.League

class LeagueAdapter(
    private val lista: List<League>,
    private val onClick: (League) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    class ViewHolder(
        val texto: TextView
    ) : RecyclerView.ViewHolder(texto)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        // Crea cada elemento de la lista de ligas
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_league,
                parent,
                false
            ) as TextView

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        // Liga correspondiente a la posición actual
        val liga = lista[position]

        // Muestra el nombre de la liga
        holder.texto.text = liga.strLeague

        // Acción al pulsar una liga
        holder.texto.setOnClickListener {
            onClick(liga)
        }
    }

    override fun getItemCount(): Int = lista.size
}