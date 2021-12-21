package br.com.marquesapps.cine.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.marquesapps.cine.model.Filme
import br.com.marquesapps.cine.model.FilmeGeneroRef
import br.com.marquesapps.cine.model.Genero
import br.com.marquesapps.cine.room.dao.FilmeComGeneroDAO
import br.com.marquesapps.cine.room.dao.FilmeDAO
import br.com.marquesapps.cine.room.dao.GeneroDAO

@Database(entities = [Filme::class, Genero::class, FilmeGeneroRef::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){
    abstract fun getFilmeDAO() : FilmeDAO
    abstract fun getGeneroDAO(): GeneroDAO
    abstract fun getFilmeComGeneroDAO(): FilmeComGeneroDAO
}