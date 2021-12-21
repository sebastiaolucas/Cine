package br.com.marquesapps.cine.room.dao

import androidx.room.*
import br.com.marquesapps.cine.model.FilmeComGenero
import br.com.marquesapps.cine.model.FilmeGeneroRef

@Dao
interface FilmeComGeneroDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(listaDeRefParaSalvar: List<FilmeGeneroRef>)

    @Transaction
    @Query("SELECT * FROM Filme WHERE id = :id")
    fun pegarFilmeComGenero(id: Int) : FilmeComGenero

}