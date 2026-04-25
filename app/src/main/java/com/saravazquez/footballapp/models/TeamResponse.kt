package com.saravazquez.footballapp.models

import com.google.gson.annotations.SerializedName

data class TeamResponse(

    // Lista de equipos recibida desde la API
    @SerializedName("teams")
    val teams: List<Team>
)