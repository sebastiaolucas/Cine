package br.com.marquesapps.cine.retrofit.model

import br.com.marquesapps.cine.model.Filme
import br.com.marquesapps.cine.model.Genero
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

private const val FORMATO_DE_ORIGEM = "yyyy-MM-dd"
private const val FORMATO_DE_SAIDA = "dd/MM/yyyy"

data class PesquisaDTO(
    val results: List<FilmeDTO>
){
    fun getParesDeFilmeEIds() : Map<Filme, List<Int>>{
        return results.map {
            val filme = Filme(
                filmeId = 0,
                id = it.id,
                nome = it.title,
                poster = it.posterPath,
                overview = it.overview,
                dataDeLancamento = formataData(it.realeaseDate),
                idiomaDeOrigem = it.originalLanguage
            )
            val generosIds = it.genresIds
            Pair(filme, generosIds)
        }.toMap()
    }

    private fun formataData(data: String?) : String{
        var dataReformata = ""
        try {
            data?.let {
                val date = SimpleDateFormat(FORMATO_DE_ORIGEM).parse(it)
                dataReformata = SimpleDateFormat(FORMATO_DE_SAIDA).format(date)
            }
        } catch (e: Exception) {
            dataReformata = ""
        }
        return dataReformata
    }
}

data class FilmeDTO(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("release_date")
    val realeaseDate: String?,
    @SerializedName("genre_ids")
    val genresIds: List<Int>
)

data class GenerosDTO(
    val genres: List<GeneroDTO>
){
    fun getListaDeGeneros(): List<Genero>{
        return genres.map {
            Genero(
                generoId = 0,
                id = it.id,
                nome = it.name
            )
        }
    }
}

data class GeneroDTO(
    val id: Int,
    val name: String
)