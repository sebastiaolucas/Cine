package br.com.marquesapps.cine

import android.app.Application
import br.com.marquesapps.cine.di.modules.dbModules
import br.com.marquesapps.cine.di.modules.repositoryModules
import br.com.marquesapps.cine.di.modules.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    dbModules,
                    repositoryModules,
                    viewModelModules
                )
            )
        }
    }

}