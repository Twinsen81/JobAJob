package com.evartem.jobajob.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jobajob.library.preferences.PreferencesApi
import jobajob.library.preferences.di.PreferencesComponent
import jobajob.library.utils.di.UtilsApi
import jobajob.library.utils.di.UtilsComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LibraryInjector @Inject constructor(@ApplicationContext private val appContext: Context) {

    fun utils(): UtilsApi = UtilsComponent.instance

    fun preferences(): PreferencesApi = PreferencesComponent.initAndGet(appContext)
}