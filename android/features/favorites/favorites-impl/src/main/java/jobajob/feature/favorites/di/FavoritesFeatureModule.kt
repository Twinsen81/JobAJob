package jobajob.feature.favorites.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jobajob.feature.favorites.FavoritesServerApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
internal object FavoritesFeatureModule {

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
    fun provideFavoritesServerApi(@FeatureInternal retrofit: Retrofit): FavoritesServerApi =
        retrofit.create(FavoritesServerApi::class.java)
}