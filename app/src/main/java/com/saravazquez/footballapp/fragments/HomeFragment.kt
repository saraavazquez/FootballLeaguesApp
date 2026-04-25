package com.saravazquez.footballapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saravazquez.footballapp.R
import com.saravazquez.footballapp.adapters.LeagueAdapter
import com.saravazquez.footballapp.data.RetrofitClient
import com.saravazquez.footballapp.models.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var recyclerLeagues: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Carga el layout principal de ligas
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Conecta el RecyclerView del layout
        recyclerLeagues = view.findViewById(R.id.recyclerLeagues)

        // Muestra los elementos en formato vertical
        recyclerLeagues.layoutManager = LinearLayoutManager(context)

        // Llama al método que carga las ligas
        cargarLigas()
    }

    private fun cargarLigas() {

        // Petición a la API para obtener todas las ligas
        RetrofitClient.api.getLeagues()
            .enqueue(object : Callback<LeagueResponse> {

                override fun onResponse(
                    call: Call<LeagueResponse>,
                    response: Response<LeagueResponse>
                ) {

                    if (response.isSuccessful) {

                        // Guarda la lista recibida o una vacía si falla
                        val lista = response.body()?.leagues ?: emptyList()

                        // Asigna el adaptador al RecyclerView
                        recyclerLeagues.adapter =
                            LeagueAdapter(lista) { liga ->

                                // Crea el fragment de equipos
                                val fragment = TeamFragment()

                                // Envía nombre e id de la liga seleccionada
                                val bundle = Bundle()
                                bundle.putString("liga", liga.strLeague)
                                bundle.putString("idLiga", liga.idLeague)

                                fragment.arguments = bundle

                                // Cambia a la pantalla de equipos
                                parentFragmentManager.beginTransaction()
                                    .replace(R.id.fragmentContainer, fragment)
                                    .addToBackStack(null)
                                    .commit()
                            }

                    } else {

                        // Muestra código de error si la respuesta falla
                        Toast.makeText(
                            context,
                            "Error ${response.code()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<LeagueResponse>,
                    t: Throwable
                ) {

                    // Error de conexión o internet
                    Toast.makeText(
                        context,
                        t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}