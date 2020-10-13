package jobajob.feature.vacancies.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jobajob.feature.vacancies.api.VacanciesBaseUrl
import jobajob.feature.vacancies.network.VacanciesServerApi
import jobajob.library.network.logger.NetworkLogger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

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
    fun provideOkHttp(
        networkLogger: NetworkLogger,
        @FeatureInternal @Named("auth") authInterceptor: Interceptor
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

        val contentType = "application/json".toMediaType()
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))

        return builder.build()
    }

    @Provides
    fun provideVacanciesServerApi(@FeatureInternal retrofit: Retrofit): VacanciesServerApi =
        retrofit.create(VacanciesServerApi::class.java)
}