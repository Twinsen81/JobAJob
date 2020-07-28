package jobajob.feature.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import jobajob.feature.dashboard.api.VacanciesGateway
import jobajob.feature.dashboard.data.local.VacanciesLocalDataSource
import jobajob.feature.dashboard.data.local.VacanciesRoomDataSource
import jobajob.feature.dashboard.data.remote.VacanciesRemoteDataSource
import jobajob.feature.dashboard.data.remote.VacanciesServerDataSource
import jobajob.feature.dashboard.data.remote.api.DashboardServerApi
import jobajob.feature.dashboard.data.repository.VacanciesRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
internal object DashboardFeatureModule {

    @Provides
    @FeatureInternal
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @FeatureInternal
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val baseUrl = "https://jobajob.herokuapp.com/"

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)

        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        builder.addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }

    @Provides
    fun provideDashboardServerApi(@FeatureInternal retrofit: Retrofit): DashboardServerApi =
        retrofit.create(DashboardServerApi::class.java)

    @Provides
    fun provideVacanciesLocalDataSource(dataSource: VacanciesRoomDataSource): VacanciesLocalDataSource = dataSource

    @Provides
    fun provideVacanciesRemoteDataSource(dataSource: VacanciesServerDataSource): VacanciesRemoteDataSource = dataSource

    @Provides
    fun provideVacanciesGateway(repository: VacanciesRepository): VacanciesGateway = repository
}