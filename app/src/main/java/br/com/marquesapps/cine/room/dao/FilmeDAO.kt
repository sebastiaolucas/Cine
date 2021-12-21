package br.com.marquesapps.cine.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import br.com.marquesapps.cine.model.Filme
import br.com.marquesapps.cine.model.FilmeComGenero

@Dao
interface FilmeDAO {

    @Insert(onConflict = REPLACE)
    fun salvar(filme: Filme) : Long

}