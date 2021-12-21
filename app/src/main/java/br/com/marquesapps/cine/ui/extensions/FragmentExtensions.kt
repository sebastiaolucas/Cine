package br.com.marquesapps.cine.ui.extensions

import androidx.fragment.app.Fragment
import br.com.marquesapps.cine.R
import br.com.marquesapps.cine.repositories.Resource
import com.google.android.material.snackbar.Snackbar


fun <T> Fragment.trataResource(
    resource: Resource<T>,
    sucesso: (T) -> Unit,
    falha: (String) -> Unit
){
    resource.messagem?.let{
        mostrarMensagem(it)
        falha(it)
    } ?: resource.dados?.let(sucesso)
}

private fun Fragment.mostrarMensagem(mensagem: String){
    Snackbar.make(
        this.requireView(),
        mensagem,
        Snackbar.LENGTH_LONG
    ).setBackgroundTint(resources.getColor(R.color.red_A400))
        .show()
}