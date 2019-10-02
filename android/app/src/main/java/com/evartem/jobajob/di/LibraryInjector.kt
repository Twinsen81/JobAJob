package com.evartem.jobajob.di

import android.app.Application
import dagger.Provides
import jobajob.library.preferences.PreferencesApi
import jobajob.library.preferences.di.PreferencesComponent
import jobajob.library.utils.di.UtilsApi
import jobajob.library.utils.di.UtilsComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LibraryInjector @Inject constructor(private val application: Application) {

    fun utils(): UtilsApi = UtilsComponent.instance

    fun preferences(): PreferencesApi = PreferencesComponent.initAndGet(application)
}