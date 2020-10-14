package jobajob.feature.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import jobajob.feature.dashboard.presentation.deeplink.VacanciesDeeplinkResolver
import jobajob.library.uicomponents.deeplink.DeeplinkResolver

@Module
@InstallIn(SingletonComponent::class)
internal object DashboardFeatureModule {

    @Provides
    @IntoMap
    @StringKey("vacancies")
    fun provideDeeplinkResolver(): DeeplinkResolver = VacanciesDeeplinkResolver()
}