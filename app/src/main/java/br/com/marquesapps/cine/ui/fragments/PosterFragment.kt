package br.com.marquesapps.cine.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.marquesapps.cine.databinding.PosterLayoutBinding
import br.com.marquesapps.cine.ui.viewmodel.PosterViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PosterFragment: Fragment() {

    private lateinit var viewDataBinding: PosterLayoutBinding
    private val args: PosterFragmentArgs by navArgs()
    private val posterViewModel: PosterViewModel by viewModel {
        parametersOf(args.pathImagem)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = PosterLayoutBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregaPoster()
    }

    private fun carregaPoster() {
        posterViewModel.pathFilm().observe(viewLifecycleOwner, {
            viewDataBinding.pathImagem = it
        })
    }

}