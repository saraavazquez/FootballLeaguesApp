package com.saravazquez.footballapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saravazquez.footballapp.R
import com.saravazquez.footballapp.adapters.TeamAdapter
import com.saravazquez.footballapp.data.FavoritesManager

class FavoritosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Carga el layout de favoritos
        return inflater.inflate(
            R.layout.fragment_favoritos,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        // Referencias a los componentes del layout
        val recycler =
            view.findViewById<RecyclerView>(R.id.recyclerFav)

        val txt =
            view.findViewById<TextView>(R.id.txtFav)

        // Título de la pantalla
        txt.text = "Mis equipos favoritos"

        // RecyclerView en vertical
        recycler.layoutManager =
            LinearLayoutManager(context)

        // Muestra la lista guardada en favoritos
        recycler.adapter =
            TeamAdapter(FavoritesManager.favoritos)
    }
}