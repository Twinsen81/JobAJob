package jobajob.library.network.logger

import okhttp3.OkHttpClient

/**
 * Логер сетевых запросов
 */
interface NetworkLogger {

    /**
     * Добавить перехватчик для логирования okHttp-запросов
     */
    fun addOkHttpInterceptor(okHttpBuilder: OkHttpClient.Builder)

    /**
     * Логировать сетевое событие
     */
    fun logEvent(eventName: String, jsonData: String)
}