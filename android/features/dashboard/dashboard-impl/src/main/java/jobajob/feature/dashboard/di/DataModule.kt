package jobajob.feature.dashboard.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import jobajob.feature.dashboard.data.local.VacanciesLocalDataSource
import jobajob.feature.dashboard.data.local.VacanciesRoomDataSource
import jobajob.feature.dashboard.data.remote.VacanciesRemoteDataSource
import jobajob.feature.dashboard.data.remote.VacanciesRetrofitDataSource
import jobajob.feature.dashboard.data.repository.VacanciesRepository
import jobajob.feature.dashboard.domain.gateway.VacanciesGateway

@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindVacanciesLocalDataSource(ds: VacanciesRoomDataSource): VacanciesLocalDataSource

    @Binds
    abstract fun bindVacanciesRemoteDataSource(ds: VacanciesRetrofitDataSource): VacanciesRemoteDataSource

    @Binds
    abstract fun bindVacanciesGateway(repository: VacanciesRepository): VacanciesGateway

    @Module
    companion object {

        @[Provides JvmStatic]
        fun provideUiScheduler() : Scheduler = AndroidSchedulers.mainThread()
    }
}