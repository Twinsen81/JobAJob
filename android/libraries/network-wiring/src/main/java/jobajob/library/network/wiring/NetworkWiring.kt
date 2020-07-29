package jobajob.library.network.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkWiring {

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }
}