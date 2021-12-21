package br.com.marquesapps.cine.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.marquesapps.cine.databinding.FilmeLayoutBinding
import br.com.marquesapps.cine.ui.databinding.EstadoDaTela
import br.com.marquesapps.cine.ui.databinding.LoadingComponentesData
import br.com.marquesapps.cine.ui.viewmodel.FilmeViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FilmeFragment: Fragment() {

    private lateinit var viewDataBinding: FilmeLayoutBinding
    private var pathImagem: String? = ""
    private val controlador by lazy { findNavController() }
    private val args: FilmeFragmentArgs by navArgs()
    private val loadingComponentesData by lazy { LoadingComponentesData() }
    private val filmeViewModel: FilmeViewModel by viewModel{
        parametersOf(args.filmeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FilmeLayoutBinding.inflate(inflater, container, false)
        viewDataBinding.loadingComponentesData = loadingComponentesData
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregaInformacoesDoFilme()
        configuraAcaoDeVizualizarPoster()
    }

    private fun configuraAcaoDeVizualizarPoster() {
        viewDataBinding.setOnPosterClickListener {
            vaiParaPosterFragment()
        }
    }

    private fun carregaInformacoesDoFilme() {
        loadingComponentesData.atualizarTela(EstadoDaTela.Carregando())
        filmeViewModel.pegarFilmeComGenero().observe(viewLifecycleOwner, {
            viewDataBinding.filme = it
            pathImagem = it.filme.poster
            loadingComponentesData.atualizarTela(EstadoDaTela.Carregada())
        })
    }

    private fun vaiParaPosterFragment(){
        val direcao = FilmeFragmentDirections
            .acaoFilmeFragmentParaPosterFragment(pathImagem)
        controlador.navigate(direcao)
    }

}