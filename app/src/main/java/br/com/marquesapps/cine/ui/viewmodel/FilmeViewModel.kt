package br.com.marquesapps.cine.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marquesapps.cine.model.FilmeComGenero
import br.com.marquesapps.cine.repositories.FilmeRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmeViewModel(
    private val filmeId: Int,
    private val filmeRepository: FilmeRepository
): ViewModel() {

    fun pegarFilmeComGenero(): LiveData<FilmeComGenero> {
        val liveData = MutableLiveData<FilmeComGenero>()
        viewModelScope.launch {
            lateinit var filmeSalvo: FilmeComGenero
            withContext(IO){
                filmeSalvo = filmeRepository.pegarFilmeComGenero(filmeId)
            }
            liveData.postValue(filmeSalvo)
        }
        return liveData
    }

}