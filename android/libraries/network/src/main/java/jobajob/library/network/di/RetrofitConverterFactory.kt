package jobajob.library.network.di

import javax.inject.Qualifier

/**
 * An annotation for Dagger's multi-binding.
 * This annotation is used to add application interceptors into a set generated by Dagger.
 * This set will be used to automatically add the interceptors to the OkHttpClient instance.
 * @see [okhttp3.OkHttpClient.Builder.addInterceptor]
 */

@Qualifier
annotation class RetrofitConverterFactory

