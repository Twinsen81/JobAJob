package jobajob.feature.dashboard.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jobajob.feature.dashboard.api.DashboardApiImpl
import jobajob.feature.dashboard.api.DashboardFeatureApi

@Module
@InstallIn(ApplicationComponent::class)
internal object DashboardWiring {

    @Provides
    fun provideDashboardFeature(apiImpl: DashboardApiImpl): DashboardFeatureApi = apiImpl
}