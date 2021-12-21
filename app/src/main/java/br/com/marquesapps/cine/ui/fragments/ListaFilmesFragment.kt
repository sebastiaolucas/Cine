package br.com.marquesapps.cine.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.marquesapps.cine.databinding.ListaFilmesLayoutBinding
import br.com.marquesapps.cine.model.Filme
import br.com.marquesapps.cine.model.FilmeComGenero
import br.com.marquesapps.cine.ui.adapter.ListaFilmeAdapter
import br.com.marquesapps.cine.ui.databinding.EstadoDaTela
import br.com.marquesapps.cine.ui.databinding.LoadingComponentesData
import br.com.marquesapps.cine.ui.extensions.trataResource
import br.com.marquesapps.cine.ui.viewmodel.ListaFilmesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListaFilmesFragment : Fragment() {

    private lateinit var viewDataBinding: ListaFilmesLayoutBinding
    private val controlador by lazy { findNavController() }
    private val loadingComponentesData by lazy { LoadingComponentesData() }
    private val listaFilmesViewModel: ListaFilmesViewModel by viewModel()
    private val adapter: ListaFilmeAdapter by lazy {
        ListaFilmeAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = ListaFilmesLayoutBinding.inflate(inflater, container, false)
        viewDataBinding.loadingComponentesData = loadingComponentesData
        return viewDataBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carregaInformacoes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraAdapter()
    }

    private fun configuraAdapter() {
        viewDataBinding.listaFilmes.adapter = adapter
        adapter.onFilmeClickListener = {
            salvarFilmeEVaiParaFilmeFragment(it)
        }
        adapter.onPosterClickListener = {
            vaiParaPosterFragment(it)
        }
    }

    private fun salvarFilmeEVaiParaFilmeFragment(filme: FilmeComGenero) {
        listaFilmesViewModel.salvaFilmeComGeneros(filme).observe(viewLifecycleOwner, {
            vaiParaFilmeFragment(filme)
        })
    }

    private fun vaiParaFilmeFragment(filme: FilmeComGenero) {
        val direcao =
            ListaFilmesFragmentDirections
                .acaoListaFilmeFragmentParaFilmeFragment(filme.filme.id)
        controlador.navigate(direcao)
    }

    private fun vaiParaPosterFragment(pathImagem: String?) {
        val direcao = ListaFilmesFragmentDirections
            .acaoListaFilmeFragmentParaPosterFragment(pathImagem)
        controlador.navigate(direcao)
    }

    private fun carregaInformacoes() {
        loadingComponentesData.atualizarTela(EstadoDaTela.Carregando())
        listaFilmesViewModel.carregaGeneros().observe(this, {
            trataResource(it, sucesso = {
                carregaFilmes()
            }, falha = {
                loadingComponentesData.atualizarTela(EstadoDaTela.CarregadaComErro())
            })
        })
    }

    private fun carregaFilmes() {
        listaFilmesViewModel.carregaListaDeFilmes().observe(this, { resource ->
            trataResource(resource, sucesso = { dto ->
                val pares = dto.getParesDeFilmeEIds()
                adaptaListaDeFilmes(pares) {
                    adapter.submitList(it)
                    loadingComponentesData.atualizarTela(EstadoDaTela.Carregada())
                }
            }, falha = {
                loadingComponentesData.atualizarTela(EstadoDaTela.CarregadaComErro())
            })
        })
    }

    private fun adaptaListaDeFilmes(
        pares: Map<Filme, List<Int>>,
        quandoAdaptar: (List<FilmeComGenero>) -> Unit
    ) {
        listaFilmesViewModel.adaptaFilmes(pares).observe(viewLifecycleOwner, {
            quandoAdaptar(it)
        })
    }

}