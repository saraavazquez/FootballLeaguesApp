package com.saravazquez.footballapp.models

import com.google.gson.annotations.SerializedName

data class LeagueResponse(

    // Lista de ligas recibida desde la API
    @SerializedName("leagues")
    val leagues: List<League>
)