package com.evartem.jobajob.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class AppNavigationModule {

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