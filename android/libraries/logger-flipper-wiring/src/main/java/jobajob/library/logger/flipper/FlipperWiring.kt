package jobajob.library.logger.flipper

import android.content.Context
import android.content.pm.ApplicationInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jobajob.library.network.logger.NetworkLogger
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FlipperWiring {

    @[Provides Singleton]
    fun provideNetworkLogger(@ApplicationContext context: Context): NetworkLogger {
        return if (isDebuggable(context)) {
            LoggerFlipper().init(context)
        } else {
            NetworkLoggerNoOp
        }
    }

    private fun isDebuggable(context: Context) =
        (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
}