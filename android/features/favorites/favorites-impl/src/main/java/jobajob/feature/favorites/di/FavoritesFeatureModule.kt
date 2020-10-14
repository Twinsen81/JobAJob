package jobajob.feature.favorites.di

/*
@Module
@InstallIn(SingletonComponent::class)
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
}*/
