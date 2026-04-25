package com.saravazquez.footballapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saravazquez.footballapp.R
import com.saravazquez.footballapp.data.FavoritesManager
import com.saravazquez.footballapp.models.Team

class TeamAdapter(
    private val lista: List<Team>
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_team, parent, false)
        )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        // Crea cada tarjeta individual del RecyclerView
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        // Equipo actual de la posición seleccionada
        val team = lista[position]

        // Referencias a los componentes de la tarjeta
        val txtTeam =
            holder.itemView.findViewById<TextView>(R.id.txtTeam)

        val imgBadge =
            holder.itemView.findViewById<ImageView>(R.id.imgBadge)

        val btnFav =
            holder.itemView.findViewById<Button>(R.id.btnFav)

        // Muestra el nombre del equipo
        txtTeam.text = team.strTeam

        // Carga imagen local con Glide
        Glide.with(holder.itemView.context)
            .load(R.drawable.football)
            .into(imgBadge)

        // Estado inicial del botón favorito
        var esFavorito = false

        btnFav.text = "☆"
        btnFav.setTextColor(Color.WHITE)

        // Al pulsar cambia entre favorito y no favorito
        btnFav.setOnClickListener {

            esFavorito = !esFavorito

            if (esFavorito) {

                // Añade a favoritos
                btnFav.text = "★"
                btnFav.setTextColor(
                    Color.parseColor("#FFD700")
                )

                FavoritesManager.agregar(team)

            } else {

                // Elimina de favoritos
                btnFav.text = "☆"
                btnFav.setTextColor(Color.WHITE)

                FavoritesManager.eliminar(team)
            }
        }
    }
}