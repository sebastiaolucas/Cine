package br.com.marquesapps.cine.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

private const val MENSAGEM_LANCAMENTO_INDISPONIVEL = "Data de lançamento indisponível"
private const val MENSAGEM_NAO_CONTEM_GENERO = "Não contêm gênero"
private const val MENSAGEM_DATA_DE_LANCAMENTO = "Data de lançamento:"
private const val MENSAGEM_GENERO = "Gêneros:"
private const val MENSAGEM_IDIOMA_DE_ORIGEM = "Idioma de origem:"

data class FilmeComGenero(
    @Embedded
    val filme: Filme,
    @Relation(
        parentColumn = "filmeId",
        entityColumn = "generoId",
        associateBy = Junction(FilmeGeneroRef::class)
    )
    val generos: List<Genero>
) {

    fun generoFormatado(): String {
        return if (generos.isEmpty()) {
            MENSAGEM_NAO_CONTEM_GENERO
        } else {
            val separados = generos.joinToString { it.nome }
            "$MENSAGEM_GENERO $separados"
        }
    }

    fun dataDeLancamentoFormatada(): String {
        val data = filme.dataDeLancamento
        return if (data.isEmpty()) {
            MENSAGEM_LANCAMENTO_INDISPONIVEL
        } else {
            "$MENSAGEM_DATA_DE_LANCAMENTO $data"
        }
    }

    fun idiomaDeOrigemFormatado(): String = "$MENSAGEM_IDIOMA_DE_ORIGEM ${filme.idiomaDeOrigem}"
}