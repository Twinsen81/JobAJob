package jobajob.feature.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import jobajob.feature.dashboard.data.DashboardApi
import jobajob.library.network.AuthInterceptor
import jobajob.library.network.di.AppInterceptor
import jobajob.library.network.di.BaseUrl
import jobajob.library.network.di.NetworkInterceptor
import jobajob.library.network.di.RetrofitModule
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
internal class DashboardNetworkModule {

    @[Provides BaseUrl]
    fun baseUrl() = "https://jobajob.herokuapp.com/"

    @[Provides IntoSet NetworkInterceptor]
    fun provideLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @[Provides IntoSet AppInterceptor]
    fun provideAuthorizationInterceptor(): Interceptor = AuthInterceptor("Bearer", "testToken")

    @Provides
    fun provideDashboardApi(retrofit: Retrofit): DashboardApi =
        retrofit.create(DashboardApi::class.java)
}
