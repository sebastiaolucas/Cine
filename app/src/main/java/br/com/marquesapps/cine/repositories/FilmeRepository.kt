package br.com.marquesapps.cine.repositories

import androidx.lifecycle.MutableLiveData
import br.com.marquesapps.cine.model.FilmeComGenero
import br.com.marquesapps.cine.model.FilmeGeneroRef
import br.com.marquesapps.cine.retrofit.model.PesquisaDTO
import br.com.marquesapps.cine.retrofit.webclients.TMDbWebClient
import br.com.marquesapps.cine.room.dao.FilmeComGeneroDAO
import br.com.marquesapps.cine.room.dao.FilmeDAO

class FilmeRepository(
    private val filmeDao: FilmeDAO,
    private val filmeComGeneroDAO: FilmeComGeneroDAO,
    private val webClient: TMDbWebClient
) {
    fun carregaListaDeFilmes(): MutableLiveData<Resource<PesquisaDTO>> {
        val liveData = MutableLiveData<Resource<PesquisaDTO>>()
        webClient.buscaFilmes(
            quandoSucesso = {
                it?.let {
                    liveData.postValue(Resource(it, null))
                }
            },
            quandoFalha = {
                liveData.postValue(Resource(null, it.message))
            }
        )
        return liveData
    }

    fun salvaFilmeComGeneros(filme: FilmeComGenero): List<FilmeGeneroRef>{
        val filmeId = filmeDao.salvar(filme.filme)
        val listaDeFilmesParaSalvar = filme.generos.map {
            FilmeGeneroRef(filmeId, it.generoId)
        }
        filmeComGeneroDAO.salva(listaDeFilmesParaSalvar)
        return listaDeFilmesParaSalvar
    }

    fun pegarFilmeComGenero(filmeId: Int): FilmeComGenero {
        return filmeComGeneroDAO.pegarFilmeComGenero(filmeId)
    }

}