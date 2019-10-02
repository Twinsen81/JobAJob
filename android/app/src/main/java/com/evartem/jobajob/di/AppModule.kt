package com.evartem.jobajob.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideApplication(): Application = application

/*    @Singleton
    @Provides
    fun provideLibraries(application: Application) = LibraryInjector(application)

    @Singleton
    @Provides
    fun provideFeatures(application: Application) = FeatureInjector(application)*/
}