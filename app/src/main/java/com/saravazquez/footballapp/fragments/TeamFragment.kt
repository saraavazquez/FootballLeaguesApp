package com.saravazquez.footballapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saravazquez.footballapp.R
import com.saravazquez.footballapp.adapters.TeamAdapter
import com.saravazquez.footballapp.data.RetrofitClient
import com.saravazquez.footballapp.models.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Carga el layout de la pantalla de equipos
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        // Referencias a los componentes del layout
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerTeams)

        // Recoge el nombre de la liga enviado desde HomeFragment
        val liga = arguments?.getString("liga") ?: "Liga"

        // Muestra el título en pantalla
        txtTitle.text = "Equipos de $liga"

        // Configura el RecyclerView en vertical
        recycler.layoutManager = LinearLayoutManager(context)

        // Recoge el id de la liga para consultar sus equipos
        val idLiga = arguments?.getString("idLiga") ?: "4328"

        // Petición a la API para obtener los equipos
        RetrofitClient.api.getTeams(idLiga)
            .enqueue(object : Callback<TeamResponse> {

                override fun onResponse(
                    call: Call<TeamResponse>,
                    response: Response<TeamResponse>
                ) {

                    if (response.isSuccessful) {

                        // Asigna el adaptador con la lista de equipos
                        recycler.adapter =
                            TeamAdapter(response.body()?.teams ?: emptyList())
                    }
                }

                override fun onFailure(
                    call: Call<TeamResponse>,
                    t: Throwable
                ) {

                    // Error de conexión o fallo de carga
                    Toast.makeText(
                        context,
                        "Error equipos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}