package com.saravazquez.footballapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.saravazquez.footballapp.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Carga el layout del login
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias a los componentes del layout
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)

        // Instancia de Firebase Authentication
        val auth = FirebaseAuth.getInstance()

        // Botón para ir a la pantalla de registro
        btnRegister.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }

        // Botón para iniciar sesión
        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Comprueba que los campos no estén vacíos
            if (email.isNotEmpty() && password.isNotEmpty()) {

                // Login con Firebase
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(
                                context,
                                "Inicio de sesión correcto",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Si todo va bien, abre la pantalla principal
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainer, HomeFragment())
                                .commit()

                        } else {

                            // Muestra el error devuelto por Firebase
                            val error =
                                task.exception?.message ?: "Error al iniciar sesión"

                            Toast.makeText(
                                context,
                                error,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

            } else {

                // Aviso si falta algún dato
                Toast.makeText(
                    context,
                    "Rellena todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}