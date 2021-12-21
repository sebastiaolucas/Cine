package br.com.marquesapps.cine.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Filme(
    @PrimaryKey(autoGenerate = true)
    val filmeId: Long,
    val id: Int,
    val nome: String,
    val poster: String?,
    val overview: String,
    val dataDeLancamento: String,
    val idiomaDeOrigem: String
)
