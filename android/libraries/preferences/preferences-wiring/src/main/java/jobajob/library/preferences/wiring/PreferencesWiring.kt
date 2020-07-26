package jobajob.library.preferences.wiring

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import jobajob.library.preferences.PreferencesApi
import jobajob.library.preferences.api.PreferencesApiImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PreferencesWiring {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): PreferencesApi = PreferencesApiImpl(context)
}