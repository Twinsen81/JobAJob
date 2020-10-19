package jobajob.feature.vacancies.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jobajob.feature.vacancies.api.VacanciesBaseUrl
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.feature.vacancies.network.FirebaseAuthInterceptor
import jobajob.feature.vacancies.network.VacanciesServerApi
import jobajob.feature.vacancies.network.dto.VacancyDto
import jobajob.feature.vacancies.network.mapper.VacancyMapper
import jobajob.feature.vacancies.usecase.GetVacanciesUseCase
import jobajob.feature.vacancies.usecase.GetVacanciesUseCaseImpl
import jobajob.library.network.logger.NetworkLogger
import jobajob.library.network.utils.Mapper
import jobajob.library.session.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
internal object VacanciesFeatureModule {

    @Provides
    @FeatureInternal
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @FeatureInternal
    fun provideAuthInterceptor(session: Session): Interceptor = FirebaseAuthInterceptor(session)

    @Provides
    @FeatureInternal
    fun provideOkHttp(
        networkLogger: NetworkLogger,
        @FeatureInternal authInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        networkLogger.addOkHttpInterceptor(builder)
        builder.addInterceptor(authInterceptor)
        return builder.build()
    }

    @Provides
    @FeatureInternal
    fun provideRetrofit(
        @FeatureInternal okHttpClient: OkHttpClient,
        @VacanciesBaseUrl baseUrl: String,
    ): Retrofit {

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            )

        return builder.build()
    }

    @Provides
    fun provideVacanciesServerApi(@FeatureInternal retrofit: Retrofit): VacanciesServerApi =
        retrofit.create(VacanciesServerApi::class.java)

    @Provides
    fun provideVacancyMapper(vacancyMapper: VacancyMapper): Mapper<VacancyDto, Vacancy> = vacancyMapper

    @Provides
    fun provideGetVacanciesUseCase(
        serverApi: VacanciesServerApi,
        vacancyMapper: Mapper<VacancyDto, Vacancy>,
        @FeatureInternal dispatcher: CoroutineDispatcher
    ): GetVacanciesUseCase = GetVacanciesUseCaseImpl(serverApi, vacancyMapper, dispatcher)
}