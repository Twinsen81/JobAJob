package jobajob.library.network.di

import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds
import jobajob.library.network.OkHttpOptions
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module
abstract class RetrofitModule {

    @Multibinds
    @OkHttpAppInterceptor
    internal abstract fun applicationInterceptors(): Set<@JvmSuppressWildcards Interceptor>

    @Multibinds
    @OkHttpNetworkInterceptor
    internal abstract fun networkInterceptors(): Set<@JvmSuppressWildcards Interceptor>

    @Multibinds
    @RetrofitCallAdapter
    internal abstract fun callAdapters(): Set<@JvmSuppressWildcards CallAdapter.Factory>

    @Multibinds
    @RetrofitConverterFactory
    internal abstract fun converterFactories(): Set<@JvmSuppressWildcards Converter.Factory>

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideOkhttp(
            @OkHttpAppInterceptor appInterceptors: Set<@JvmSuppressWildcards Interceptor>,
            @OkHttpNetworkInterceptor networkInterceptors: Set<@JvmSuppressWildcards Interceptor>,
            options: OkHttpOptions? = null
        ): OkHttpClient {
            val builder = OkHttpClient.Builder()

            options?.apply {
                builder.connectTimeout(connectTimeoutSeconds, TimeUnit.SECONDS)
                builder.readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)
                builder.writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS)
                builder.callTimeout(callTimeoutSeconds, TimeUnit.SECONDS)
            }

            appInterceptors.forEach { builder.addInterceptor(it) }
            networkInterceptors.forEach { builder.addNetworkInterceptor(it) }

            return builder.build()
        }

        @Provides
        @JvmStatic
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            @BaseUrl baseUrl: String,
            @RetrofitCallAdapter callAdapters: Set<@JvmSuppressWildcards CallAdapter.Factory>,
            @RetrofitConverterFactory converters: Set<@JvmSuppressWildcards Converter.Factory>
        ): Retrofit {

            val builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)

            callAdapters.forEach { builder.addCallAdapterFactory(it) }
            converters.forEach { builder.addConverterFactory(it) }

            return builder.build()
        }
    }
}