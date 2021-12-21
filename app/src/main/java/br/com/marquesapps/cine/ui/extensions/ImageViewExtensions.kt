package br.com.marquesapps.cine.ui.extensions

import android.widget.ImageView
import br.com.marquesapps.cine.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

private const val PREFIXO_PARA_O_LINK_DO_POSTER = "https://image.tmdb.org/t/p/w500"

fun ImageView.carregarImagem(pathImagem: String?){
    pathImagem?.let {
        Glide.with(this.context)
            .load("$PREFIXO_PARA_O_LINK_DO_POSTER$it")
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .centerCrop()
            .placeholder(R.drawable.ic_movie_24)
            .into(this)
    }
}