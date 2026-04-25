package com.saravazquez.footballapp.models

import com.google.gson.annotations.SerializedName

data class Team(

    // Nombre del equipo
    @SerializedName("strTeam")
    val strTeam: String,

    // Escudo o imagen del equipo. Puede venir vacío, por eso es nullable
    @SerializedName("strTeamBadge")
    val strTeamBadge: String?
)