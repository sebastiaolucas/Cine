package br.com.marquesapps.cine.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PosterViewModel(
   private val pathFilm: String
): ViewModel() {

    fun pathFilm() : LiveData<String> =
        MutableLiveData<String>().also {
            it.value = pathFilm
        }
}