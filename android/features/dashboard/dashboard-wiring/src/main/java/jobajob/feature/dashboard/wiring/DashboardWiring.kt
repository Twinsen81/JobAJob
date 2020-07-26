package jobajob.feature.dashboard.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import jobajob.feature.dashboard.api.DashboardApiImpl
import jobajob.feature.dashboard.api.DashboardFeatureApi

@Module
@InstallIn(ActivityComponent::class)
object DashboardWiring {

    @Provides
    fun provideDashboard(): DashboardFeatureApi = DashboardApiImpl
}