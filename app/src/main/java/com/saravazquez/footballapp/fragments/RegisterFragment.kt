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

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Carga el layout del registro
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias a los componentes del layout
        val etEmail = view.findViewById<EditText>(R.id.etEmailRegister)
        val etPassword = view.findViewById<EditText>(R.id.etPasswordRegister)
        val btnRegister = view.findViewById<Button>(R.id.btnDoRegister)

        // Instancia de Firebase Authentication
        val auth = FirebaseAuth.getInstance()

        // Botón para registrar usuario
        btnRegister.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Comprueba que los campos tengan datos
            if (email.isNotEmpty() && password.isNotEmpty()) {

                // Crear usuario en Firebase
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(
                                context,
                                "Usuario creado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Si el registro va bien, vuelve al login
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainer, LoginFragment())
                                .commit()

                        } else {

                            // Muestra el error devuelto por Firebase
                            val error =
                                task.exception?.message ?: "Error desconocido"

                            Toast.makeText(
                                context,
                                "Error: $error",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

            } else {

                // Aviso si faltan campos
                Toast.makeText(
                    context,
                    "Rellena todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}