package jobajob.library.uicomponents.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(ApplicationComponent::class)
object OkhttpModule {

    @Provides
    fun provideOkhttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }
}
