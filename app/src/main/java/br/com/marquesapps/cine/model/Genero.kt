package br.com.marquesapps.cine.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genero(
    @PrimaryKey(autoGenerate = true)
    val generoId: Long,
    val id: Int,
    val nome: String
)