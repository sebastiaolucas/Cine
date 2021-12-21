package br.com.marquesapps.cine.di.modules

import androidx.room.Room
import br.com.marquesapps.cine.BuildConfig
import br.com.marquesapps.cine.repositories.FilmeRepository
import br.com.marquesapps.cine.repositories.GeneroRepository
import br.com.marquesapps.cine.retrofit.AppRetrofit
import br.com.marquesapps.cine.retrofit.webclients.TMDbWebClient
import br.com.marquesapps.cine.room.AppDataBase
import br.com.marquesapps.cine.room.dao.FilmeComGeneroDAO
import br.com.marquesapps.cine.room.dao.FilmeDAO
import br.com.marquesapps.cine.room.dao.GeneroDAO
import br.com.marquesapps.cine.ui.viewmodel.FilmeViewModel
import br.com.marquesapps.cine.ui.viewmodel.ListaFilmesViewModel
import br.com.marquesapps.cine.ui.viewmodel.PosterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModules = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            BuildConfig.NOME_DO_BANCO_DE_DADOS
        ).build()
    }
}

val repositoryModules = module{
    single<AppRetrofit> { AppRetrofit() }
    single<TMDbWebClient> { TMDbWebClient(get<AppRetrofit>().tmDbService) }
    single<GeneroDAO> { get<AppDataBase>().getGeneroDAO() }
    single<FilmeDAO> { get<AppDataBase>().getFilmeDAO() }
    single<FilmeComGeneroDAO> { get<AppDataBase>().getFilmeComGeneroDAO() }
    single<GeneroRepository> { GeneroRepository(get(), get()) }
    single<FilmeRepository> { FilmeRepository(get(), get(), get()) }
}

val viewModelModules = module {
    viewModel<ListaFilmesViewModel>{ ListaFilmesViewModel(get(), get()) }
    viewModel<FilmeViewModel> { FilmeViewModel(get(), get()) }
    viewModel<PosterViewModel> { PosterViewModel(get()) }
}