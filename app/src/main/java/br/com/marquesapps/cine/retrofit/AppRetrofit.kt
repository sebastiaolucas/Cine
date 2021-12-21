package br.com.marquesapps.cine.retrofit

import br.com.marquesapps.cine.BuildConfig
import br.com.marquesapps.cine.retrofit.services.TMDbService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofit {

    private val retrofit: Retrofit by lazy{
        val interceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val tmDbService = retrofit.create(TMDbService::class.java)

}