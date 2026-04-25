package com.saravazquez.footballapp.data

import com.saravazquez.footballapp.models.Team

object FavoritesManager {

    // Lista donde se guardan los equipos favoritos
    val favoritos = mutableListOf<Team>()

    fun agregar(team: Team) {

        // Añade el equipo solo si no está repetido
        if (!favoritos.contains(team)) {
            favoritos.add(team)
        }
    }

    fun eliminar(team: Team) {

        // Elimina el equipo de favoritos
        favoritos.remove(team)
    }
}