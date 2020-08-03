package jobajob.feature.dashboard.devapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jobajob.feature.dashboard.api.DashboardApiImpl
import jobajob.feature.dashboard.api.DashboardBaseUrl
import jobajob.feature.dashboard.api.DashboardFeatureApi

@Module
@InstallIn(ApplicationComponent::class)
object DashboardDevAppWiring {

    @Provides
    fun provideDashboardFeature(apiImpl: DashboardApiImpl): DashboardFeatureApi = apiImpl

    @Provides
    @DashboardBaseUrl
    fun getBaseUrl() = "https://jobajob.herokuapp.com/"
}