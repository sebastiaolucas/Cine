package br.com.marquesapps.cine.retrofit.webclients

import br.com.marquesapps.cine.retrofit.model.GenerosDTO
import br.com.marquesapps.cine.retrofit.model.PesquisaDTO
import br.com.marquesapps.cine.retrofit.services.TMDbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val MENSAGEM_DE_ERRO = "Ocorreu um erro"

class TMDbWebClient(
    private val service: TMDbService
) {

    private fun <T> getResposta(
        call: Call<T>,
        quandoSucesso: (T) -> Unit,
        quandoFalha: (Throwable) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        quandoSucesso(it)
                    } ?: quandoFalha(Throwable(MENSAGEM_DE_ERRO))
                } else {
                    quandoFalha(Throwable(MENSAGEM_DE_ERRO))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                quandoFalha(Throwable(MENSAGEM_DE_ERRO))
            }

        })
    }

    fun buscaFilmes(
        quandoSucesso: (PesquisaDTO?) -> Unit,
        quandoFalha: (Throwable) -> Unit
    ) = getResposta(
        service.buscarFilmesEmCartaz(),
        quandoSucesso,
        quandoFalha
    )

    fun buscaGeneros(
        quandoSucesso: (GenerosDTO?) -> Unit,
        quandoFalha: (Throwable) -> Unit
    ) = getResposta(
        service.buscarListaDeGeneros(),
        quandoSucesso,
        quandoFalha
    )

}