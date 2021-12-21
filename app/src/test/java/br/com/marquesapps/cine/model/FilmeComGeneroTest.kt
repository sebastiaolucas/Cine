package br.com.marquesapps.cine.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class FilmeComGeneroTest {

    @Test
    fun deve_RetornarUmaMensagemMaisADataFormatadaNoPadraoBrasileiro_QuandoReceberDataCorretamente() {
        val filme = Filme(
            0,
            0,
            "Homem aranha",
            "/poster.jpg",
            "Um bom Filme",
            "15/12/2000",
            "en",
        )
        val filmeComGenero = FilmeComGenero(filme, listOf())

        val dataDeLancamentoFormatada = filmeComGenero.dataDeLancamentoFormatada()

        assertThat(dataDeLancamentoFormatada, `is`("Data de lançamento: 15/12/2000"))
    }

    @Test
    fun deve_RetornarUmaMensagemDeIndisponibilidadeDeData_QuandoNaoReceberData() {
        val filme = Filme(
            0,
            0,
            "Homem aranha",
            "/poster.jpg",
            "Um bom Filme",
            "",
            "en",
        )
        val filmeComGenero = FilmeComGenero(filme, listOf())

        val dataDeLancamentoFormatada = filmeComGenero.dataDeLancamentoFormatada()

        assertThat(dataDeLancamentoFormatada, `is`("Data de lançamento indisponível"))
    }

    @Test
    fun deve_RetornarUmaMensagemComOsGenerosSeparosPorVirgula_QuandoReceberUmaListaDeGeneros() {
        val filme = Filme(
            0,
            0,
            "Homem aranha",
            "/poster.jpg",
            "Um bom Filme",
            "2000-12-15",
            "en",
        )
        val filmeComGenero = FilmeComGenero(filme, listOf(Genero(0, 0, "Ação")))

        val generoFormatado = filmeComGenero.generoFormatado()

        assertThat(generoFormatado, `is`("Gêneros: Ação"))
    }

    @Test
    fun deve_RetornarUmaMensagemDeNaoContem_QuandoNaoReceberUmaListaDeGeneros() {
        val filme = Filme(
            0,
            0,
            "Homem aranha",
            "/poster.jpg",
            "Um bom Filme",
            "2000-12-15",
            "en",
        )
        val filmeComGenero = FilmeComGenero(filme, listOf())

        val generoFormatado = filmeComGenero.generoFormatado()

        assertThat(generoFormatado, `is`("Não contêm gênero"))
    }

}