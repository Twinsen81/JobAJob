package jobajob.feature.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import jobajob.feature.dashboard.data.remote.api.DashboardApi
import jobajob.library.network.di.*
import jobajob.library.utils.di.PerFeature
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [RetrofitModule::class])
internal class NetworkModule {

    @[Provides BaseUrl]
    fun baseUrl() = "https://jobajob.herokuapp.com/"

    @[Provides IntoSet OkHttpNetworkInterceptor]
    fun provideLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    //@[Provides IntoSet OkHttpAppInterceptor]
    //fun provideAuthorizationInterceptor(): Interceptor = AuthInterceptor("Bearer", "testToken")

    @[Provides IntoSet RetrofitCallAdapter]
    fun provideRxAdapter(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @[Provides IntoSet RetrofitConverterFactory]
    fun provideGsonConverter(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @PerFeature
    fun provideDashboardApi(retrofit: Retrofit): DashboardApi =
        retrofit.create(DashboardApi::class.java)
}
