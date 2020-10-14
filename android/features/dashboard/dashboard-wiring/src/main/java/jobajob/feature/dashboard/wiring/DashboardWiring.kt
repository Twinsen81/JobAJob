package jobajob.feature.dashboard.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jobajob.feature.dashboard.api.DashboardApiImpl
import jobajob.feature.dashboard.api.DashboardFeatureApi

@Module
@InstallIn(SingletonComponent::class)
internal object DashboardWiring {

    @Provides
    fun provideDashboardFeature(apiImpl: DashboardApiImpl): DashboardFeatureApi = apiImpl
}