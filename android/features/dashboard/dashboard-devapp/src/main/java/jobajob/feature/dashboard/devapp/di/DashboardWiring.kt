package jobajob.feature.dashboard.devapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jobajob.feature.dashboard.api.DashboardApiImpl
import jobajob.feature.dashboard.api.DashboardFeatureApi
import jobajob.library.session.Session
import jobajob.library.session.SessionManager
import jobajob.library.session.SessionManagerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object DashboardDevAppWiring {

    @Provides
    fun provideDashboardFeature(apiImpl: DashboardApiImpl): DashboardFeatureApi = apiImpl

    @[Provides Singleton]
    fun provideSessionManager(): SessionManager = SessionManagerImpl()

    @Provides
    fun provideSession(sessionManager: SessionManager): Session = sessionManager
}