package com.saravazquez.footballapp.models

import com.google.gson.annotations.SerializedName

data class League(

    // Id único de la liga
    @SerializedName("idLeague")
    val idLeague: String,

    // Nombre de la liga
    @SerializedName("strLeague")
    val strLeague: String
)