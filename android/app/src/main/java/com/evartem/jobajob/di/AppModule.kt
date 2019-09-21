package com.evartem.jobajob.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext() = application

    @Singleton
    @Provides
    fun provideApplication() = application
}