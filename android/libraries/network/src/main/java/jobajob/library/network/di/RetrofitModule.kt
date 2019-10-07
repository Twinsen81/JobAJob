package jobajob.library.network.di

import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class RetrofitModule {

    @Multibinds
    @AppInterceptor
    internal abstract fun applicationInterceptors(): Set<Interceptor>

    @Multibinds
    @NetworkInterceptor
    internal abstract fun networkInterceptors(): Set<Interceptor>

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideOkhttp(
            //@AppInterceptor appInterceptors: Set<Interceptor>,
            //@NetworkInterceptor networkInterceptors: Set<Interceptor>
        ): OkHttpClient {
            val builder = OkHttpClient.Builder()
            //appInterceptors.forEach { builder.addInterceptor(it) }
            //networkInterceptors.forEach { builder.addNetworkInterceptor(it) }
            return builder.build()
        }

        @Provides
        @JvmStatic
        fun provideRetrofit(
            okHttpClient: Lazy<OkHttpClient>,
            @BaseUrl baseUrl: String
        ): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory(okHttpClient.get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}