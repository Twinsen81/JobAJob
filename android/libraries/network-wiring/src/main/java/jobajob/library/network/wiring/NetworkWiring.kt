package jobajob.library.network.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jobajob.library.network.logger.NetworkLogger
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkWiring {

    @Provides
    @Singleton
    fun provideOkhttp(networkLogger: NetworkLogger): OkHttpClient {
        val builder = OkHttpClient.Builder()
        networkLogger.addOkHttpInterceptor(builder)
        return builder.build()
    }
}