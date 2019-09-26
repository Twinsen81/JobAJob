package jobajob.library.preferences.di

import dagger.Binds
import dagger.Module
import jobajob.library.preferences.SharedPreferencesRepository
import jobajob.library.preferences.PreferencesRepository
import javax.inject.Singleton

@Module
internal abstract class PreferencesModule {

    @Singleton
    @Binds
    abstract fun providePreferencesRepository(sharedPrefsImp: SharedPreferencesRepository): PreferencesRepository
}