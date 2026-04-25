package com.saravazquez.footballapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

object RetrofitClient {

    // Cliente personalizado para evitar problemas SSL en el emulador
    private fun getUnsafeClient(): OkHttpClient {

        val trustAllCerts = arrayOf<TrustManager>(

            object : X509TrustManager {

                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers():
                        Array<X509Certificate> = arrayOf()
            }
        )

        // Configuración SSL
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .sslSocketFactory(
                sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            .hostnameVerifier { _, _ -> true }
            .build()
    }

    // Instancia global de Retrofit
    val api: ApiService by lazy {

        Retrofit.Builder()

            // URL principal de la API
            .baseUrl("https://www.thesportsdb.com/")

            // Cliente configurado
            .client(getUnsafeClient())

            // Conversor JSON a objetos Kotlin
            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

            // Crea la interfaz ApiService
            .create(ApiService::class.java)
    }
}