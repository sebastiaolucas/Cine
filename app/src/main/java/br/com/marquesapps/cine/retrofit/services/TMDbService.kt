package br.com.marquesapps.cine.retrofit.services

import br.com.marquesapps.cine.BuildConfig
import br.com.marquesapps.cine.retrofit.model.GenerosDTO
import br.com.marquesapps.cine.retrofit.model.PesquisaDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val LINGUAGEM_PADRAO = "pt-BR"

interface TMDbService {

    @GET("discover/movie")
    fun buscarFilmesEmCartaz(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") linguagem: String = LINGUAGEM_PADRAO
    ) : Call<PesquisaDTO?>

    @GET("genre/movie/list")
    fun buscarListaDeGeneros(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") linguagem: String = LINGUAGEM_PADRAO,
    ) : Call<GenerosDTO>
}