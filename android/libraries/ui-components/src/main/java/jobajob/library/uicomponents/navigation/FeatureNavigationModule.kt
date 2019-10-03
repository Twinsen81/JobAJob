package jobajob.library.uicomponents.navigation

import dagger.Module
import dagger.Provides
import jobajob.library.utils.di.PerFeature
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class FeatureNavigationModule {

    @PerFeature
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @PerFeature
    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @PerFeature
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}