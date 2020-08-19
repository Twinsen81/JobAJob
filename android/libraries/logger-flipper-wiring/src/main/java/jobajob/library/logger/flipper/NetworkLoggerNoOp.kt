package jobajob.library.logger.flipper

import jobajob.library.network.logger.NetworkLogger
import okhttp3.OkHttpClient.Builder

/**
 * No-op версия сетевого логера. Используется для release-сборки
 */
object NetworkLoggerNoOp : NetworkLogger {
    override fun addOkHttpInterceptor(okHttpBuilder: Builder) = Unit

    override fun logEvent(eventName: String, jsonData: String) = Unit
}