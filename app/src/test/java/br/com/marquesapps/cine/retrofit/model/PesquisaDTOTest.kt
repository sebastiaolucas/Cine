package br.com.marquesapps.cine.retrofit.model

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class PesquisaDTOTest {

    @Test
    fun deve_RetornarDataFormatadaNoPadraoBrasileiro_QuandoReceberDataCorretamente() {
        val dto = PesquisaDTO(
            listOf(
                FilmeDTO(
                    0,
                    "Homem aranha",
                    "/poster.jpg",
                    "Um bom Filme",
                    "en",
                    "2020-12-15",
                    listOf()
                )
            )
        )

        val filme = dto.getParesDeFilmeEIds().keys.first()

        assertThat(filme.dataDeLancamento, `is`("15/12/2020"))
    }

    @Test
    fun deve_RetornarDataVazia_QuandoReceberDataIncorretamente() {
        val dto = PesquisaDTO(
            listOf(
                FilmeDTO(
                    0,
                    "Homem aranha",
                    "/poster.jpg",
                    "Um bom Filme",
                    "en",
                    "incorreto",
                    listOf()
                )
            )
        )

        val filme = dto.getParesDeFilmeEIds().keys.first()

        assertThat(filme.dataDeLancamento, `is`(""))
    }

    @Test
    fun deve_RetornarDataVazia_QuandoReceberDataVazia() {
        val dto = PesquisaDTO(
            listOf(
                FilmeDTO(
                    0,
                    "Homem aranha",
                    "/poster.jpg",
                    "Um bom Filme",
                    "en",
                    "",
                    listOf()
                )
            )
        )

        val filme = dto.getParesDeFilmeEIds().keys.first()

        assertThat(filme.dataDeLancamento, `is`(""))
    }

    @Test
    fun deve_RetornarDataVazia_QuandoReceberDataNula() {
        val dto = PesquisaDTO(
            listOf(
                FilmeDTO(
                    0,
                    "Homem aranha",
                    "/poster.jpg",
                    "Um bom Filme",
                    "en",
                    null,
                    listOf()
                )
            )
        )

        val filme = dto.getParesDeFilmeEIds().keys.first()

        assertThat(filme.dataDeLancamento, `is`(""))
    }

}