package br.com.marquesapps.cine.ui.databinding

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.ObservableField

data class LoadingComponentesData(
    val messagemDeErroVisibility: ObservableField<Int> = ObservableField<Int>(),
    val corpoVisibility: ObservableField<Int> = ObservableField<Int>(),
    val progressoVisibility: ObservableField<Int> = ObservableField<Int>()
){

    init {
        atualizarTela(EstadoDaTela.Carregando())
    }

    fun atualizarTela(estado: EstadoDaTela){
        this.messagemDeErroVisibility.set(estado.messagemDeErro)
        this.corpoVisibility.set(estado.corpo)
        this.progressoVisibility.set(estado.progresso)
    }

}

sealed class EstadoDaTela(
    val messagemDeErro: Int,
    val corpo: Int,
    val progresso: Int
){
    class Carregando : EstadoDaTela(messagemDeErro = GONE, corpo = GONE, progresso = VISIBLE)
    class Carregada : EstadoDaTela(messagemDeErro = GONE, corpo = VISIBLE, progresso = GONE)
    class CarregadaComErro : EstadoDaTela(messagemDeErro = VISIBLE, corpo = GONE, progresso = GONE)
}
