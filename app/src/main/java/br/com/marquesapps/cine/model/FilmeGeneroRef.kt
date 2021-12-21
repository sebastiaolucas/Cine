package br.com.marquesapps.cine.model

import androidx.room.Entity

@Entity(primaryKeys = ["filmeId", "generoId"])
data class FilmeGeneroRef(
    val filmeId: Long,
    val generoId: Long
)
