package com.saravazquez.footballapp.data

import com.saravazquez.footballapp.models.LeagueResponse
import com.saravazquez.footballapp.models.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Obtiene todas las ligas disponibles
    @GET("api/v1/json/123/all_leagues.php")
    fun getLeagues(): Call<LeagueResponse>

    // Obtiene los equipos de una liga según su id
    @GET("api/v1/json/123/search_all_teams.php")
    fun getTeams(
        @Query("id") id: String
    ): Call<TeamResponse>
}