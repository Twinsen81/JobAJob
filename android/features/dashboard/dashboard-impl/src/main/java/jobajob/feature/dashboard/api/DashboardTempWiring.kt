package jobajob.feature.dashboard.api

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import jobajob.feature.dashboard.data.local.VacanciesLocalDataSource
import jobajob.feature.dashboard.data.local.VacanciesRoomDataSource
import jobajob.feature.dashboard.data.remote.VacanciesRemoteDataSource
import jobajob.feature.dashboard.data.remote.VacanciesRetrofitDataSource
import jobajob.feature.dashboard.data.remote.api.DashboardApi
import jobajob.feature.dashboard.data.repository.VacanciesRepository
import jobajob.feature.dashboard.domain.gateway.VacanciesGateway
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
internal object DashboardTempWiring {
    @Provides
    @Singleton
    fun provideDashboardApi(retrofit: Retrofit): DashboardApi = retrofit.create(DashboardApi::class.java)

    @Provides
    fun provideUiScheduler() : Scheduler = AndroidSchedulers.mainThread()
}

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun bindVacanciesLocalDataSource(ds: VacanciesRoomDataSource): VacanciesLocalDataSource

    @Binds
    abstract fun bindVacanciesRemoteDataSource(ds: VacanciesRetrofitDataSource): VacanciesRemoteDataSource

    @Binds
    abstract fun bindVacanciesGateway(repository: VacanciesRepository): VacanciesGateway
}