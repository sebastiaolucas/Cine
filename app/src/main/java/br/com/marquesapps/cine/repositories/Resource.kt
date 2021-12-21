package br.com.marquesapps.cine.repositories

data class Resource<T>(
    val dados: T?,
    val messagem: String?
)
