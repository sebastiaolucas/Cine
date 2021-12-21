package br.com.marquesapps.cine.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.marquesapps.cine.databinding.FilmeItemBinding
import br.com.marquesapps.cine.model.FilmeComGenero

class ListaFilmeAdapter(
    private val context: Context,
) : ListAdapter<FilmeComGenero, ListaFilmeAdapter.FilmeViewHolder>(ListaFilmeAdapter) {

    var onFilmeClickListener: (FilmeComGenero) -> Unit = {}
    var onPosterClickListener: (String?) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val inflater = LayoutInflater.from(context)
        val viewDataBinding = FilmeItemBinding.inflate(inflater, parent, false)
        return FilmeViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.vincula(getItem(position))
    }


    inner class FilmeViewHolder(private val viewDataBinding: FilmeItemBinding)
        : RecyclerView.ViewHolder(viewDataBinding.root){

        fun vincula(filme: FilmeComGenero){
            viewDataBinding.filme = filme
            viewDataBinding.setOnPosterClickListener {
                onPosterClickListener(filme.filme.poster)
            }
            viewDataBinding.setOnFilmeClickListener {
                onFilmeClickListener(filme)
            }
        }

    }

    private companion object : DiffUtil.ItemCallback<FilmeComGenero>(){
        override fun areItemsTheSame(oldItem: FilmeComGenero, newItem: FilmeComGenero): Boolean {
            return oldItem.filme.filmeId == newItem.filme.filmeId
        }

        override fun areContentsTheSame(oldItem: FilmeComGenero, newItem: FilmeComGenero): Boolean {
            return oldItem == newItem
        }

    }
}