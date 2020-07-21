package jobajob.library.network

import okhttp3.OkHttpClient

/**
 * No-op версия для работы сетевого плагина Flipper. Используется для релизной сборки
 */
object FlipperNetworkPluginHelper {

    fun addOkHttpInterceptor(okHttpBuilder: OkHttpClient.Builder) = Unit

    fun logEvent(eventName: String, jsonData: String) = Unit
}