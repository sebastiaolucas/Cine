package br.com.marquesapps.cine.repositories

import br.com.marquesapps.cine.model.Filme
import br.com.marquesapps.cine.model.FilmeComGenero
import br.com.marquesapps.cine.retrofit.webclients.TMDbWebClient
import br.com.marquesapps.cine.room.dao.GeneroDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeneroRepository(
    private val dao: GeneroDAO,
    private val webClient: TMDbWebClient
) {

    suspend fun carregaGeneros(
        quandoCarrega: (Resource<Boolean>) -> Unit
    ) {
        dao.getGeneros().let { lista ->
            if (lista.isEmpty()) {
                carregaGenerosDaApi(quandoCarrega)
            } else {
                quandoCarrega(Resource(true, null))
            }
        }
    }

    suspend fun adaptaFilmes(pares: Map<Filme, List<Int>>) : List<FilmeComGenero>{
        return pares.map { par ->
            adaptarFilmesUsandoIds(par.toPair())
        }
    }

    private suspend fun adaptarFilmesUsandoIds(par: Pair<Filme, List<Int>>): FilmeComGenero{
        lateinit var filmeComGenero: FilmeComGenero
        dao.getGeneros(par.second).let { generos ->
            filmeComGenero = FilmeComGenero(par.first, generos)
        }
        return filmeComGenero
    }

     private fun carregaGenerosDaApi(
         quandoCarrega: (Resource<Boolean>) -> Unit
     ) {
        webClient.buscaGeneros(
            quandoSucesso = { dto ->
                dto?.let {
                    val generos = it.getListaDeGeneros()
                    CoroutineScope(Dispatchers.IO).launch {
                        dao.salvarGeneros(generos)
                    }
                    quandoCarrega(Resource(true, null))
                }
            },
            quandoFalha = {
                quandoCarrega(Resource(false, it.message))
            }
        )
    }
}