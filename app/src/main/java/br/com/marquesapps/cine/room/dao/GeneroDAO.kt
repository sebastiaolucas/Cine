package br.com.marquesapps.cine.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.marquesapps.cine.model.Genero
import kotlinx.coroutines.flow.Flow

@Dao
interface GeneroDAO {

    @Query("SELECT * FROM Genero")
    suspend fun getGeneros(): List<Genero>

    @Insert(onConflict = REPLACE)
    suspend fun salvarGeneros(generos: List<Genero>)

    @Query("SELECT * FROM Genero WHERE id IN (:generoIds)")
    suspend fun getGeneros(generoIds: List<Int>) : List<Genero>

}