package jobajob.library.network.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jobajob.library.network.logger.NetworkLogger
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkWiring {

    @Provides
    @Singleton
    fun provideOkhttp(networkLogger: NetworkLogger): OkHttpClient {
        val builder = OkHttpClient.Builder()
        networkLogger.addOkHttpInterceptor(builder)
        return builder.build()
    }
}