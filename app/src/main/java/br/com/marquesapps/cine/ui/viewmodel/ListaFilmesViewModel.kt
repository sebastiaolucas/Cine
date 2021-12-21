package br.com.marquesapps.cine.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marquesapps.cine.model.Filme
import br.com.marquesapps.cine.model.FilmeComGenero
import br.com.marquesapps.cine.repositories.FilmeRepository
import br.com.marquesapps.cine.repositories.GeneroRepository
import br.com.marquesapps.cine.repositories.Resource
import br.com.marquesapps.cine.retrofit.model.PesquisaDTO
import br.com.marquesapps.cine.ui.databinding.EstadoDaTela
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaFilmesViewModel(
    private val generoRepository: GeneroRepository,
    private val filmeRepository: FilmeRepository
) : ViewModel() {

    fun carregaGeneros(): LiveData<Resource<Boolean>> {
        val mutableLiveData = MutableLiveData<Resource<Boolean>>()
        viewModelScope.launch {
            withContext(IO) {
                generoRepository.carregaGeneros(
                    quandoCarrega = {
                        mutableLiveData.postValue(Resource(true, null))
                    }
                )
            }
        }
        return mutableLiveData
    }

    fun carregaListaDeFilmes(): LiveData<Resource<PesquisaDTO>> {
        return filmeRepository.carregaListaDeFilmes()
    }

    fun adaptaFilmes(pares: Map<Filme, List<Int>>): LiveData<List<FilmeComGenero>> {
        val liveData = MutableLiveData<List<FilmeComGenero>>()
        viewModelScope.launch {
            lateinit var lista: List<FilmeComGenero>
            withContext(IO) {
                lista = generoRepository.adaptaFilmes(pares)
            }
            liveData.postValue(lista)
        }
        return liveData
    }

    fun salvaFilmeComGeneros(filme: FilmeComGenero): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        viewModelScope.launch {
            lateinit var filmeSalvo: FilmeComGenero
            withContext(IO) {
                filmeRepository.salvaFilmeComGeneros(filme)
            }
            liveData.postValue(Resource(true, null))
        }
        return liveData
    }

}