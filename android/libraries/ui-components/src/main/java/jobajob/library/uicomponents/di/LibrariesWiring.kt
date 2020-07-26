package jobajob.library.uicomponents.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jobajob.library.utils.KeyStoreUtils
import jobajob.library.utils.Utils
import jobajob.library.utils.di.UtilsApi
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LibrariesWiring {

    @Provides
    fun provideUtils(): UtilsApi = object : UtilsApi {
        override fun utils(): Utils = Utils()
        override fun keyStoreUtils(): KeyStoreUtils = KeyStoreUtils()
    }

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Singleton
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}