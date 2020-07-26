package jobajob.library.uicomponents.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Provides
    fun provideOkhttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder.addInterceptor(loggingInterceptor)
        //networkInterceptors.forEach { builder.addNetworkInterceptor(it) }

        return builder.build()
    }

        @[Provides JvmStatic]
        fun provideRetrofit(
            okHttpClient: OkHttpClient
        ): Retrofit {

            val baseUrl = "https://jobajob.herokuapp.com/"

            val builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)


            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.addConverterFactory(GsonConverterFactory.create())

            return builder.build()
        }
}
