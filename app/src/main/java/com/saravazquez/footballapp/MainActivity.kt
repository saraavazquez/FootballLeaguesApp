package com.saravazquez.footballapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.saravazquez.footballapp.fragments.FavoritosFragment
import com.saravazquez.footballapp.fragments.HomeFragment
import com.saravazquez.footballapp.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Carga el layout principal de la aplicación
        setContentView(R.layout.activity_main)

        // Conecta la Toolbar con la actividad
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Al iniciar no mostramos la flecha de volver atrás
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // Controla si hay fragments en la pila para mostrar u ocultar la flecha
        supportFragmentManager.addOnBackStackChangedListener {

            val canGoBack = supportFragmentManager.backStackEntryCount > 0
            supportActionBar?.setDisplayHomeAsUpEnabled(canGoBack)
        }

        // Acción de la flecha de la Toolbar
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Si es la primera vez que se abre la app, carga LoginFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .commit()
        }

        // Control del botón atrás del dispositivo
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {

                    // Si hay fragments anteriores, vuelve atrás
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        supportFragmentManager.popBackStack()
                    } else {
                        // Si no hay más pantallas, cierra la app
                        finish()
                    }
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // Carga el menú superior de la aplicación
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            // Opción para ir a la pantalla principal
            R.id.menuInicio -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, HomeFragment())
                    .addToBackStack(null)
                    .commit()
            }

            // Opción para ver equipos favoritos
            R.id.menuFavoritos -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, FavoritosFragment())
                    .addToBackStack(null)
                    .commit()
            }

            // Opción para cerrar sesión y volver al login
            R.id.menuSalir -> {

                FirebaseAuth.getInstance().signOut()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, LoginFragment())
                    .commit()
            }
        }

        return true
    }
}