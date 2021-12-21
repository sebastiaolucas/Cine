package br.com.marquesapps.cine.ui.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.marquesapps.cine.ui.extensions.carregarImagem


@BindingAdapter("android:carregaImagem")
fun ImageView.carregaImagemAdapter(pathImagem: String?){
    this.carregarImagem(pathImagem)
}