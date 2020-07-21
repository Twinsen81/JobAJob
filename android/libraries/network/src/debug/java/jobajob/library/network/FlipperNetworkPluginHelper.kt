package jobajob.library.network

import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkReporter.Header
import com.facebook.flipper.plugins.network.NetworkReporter.RequestInfo
import com.facebook.flipper.plugins.network.NetworkReporter.ResponseInfo
import okhttp3.OkHttpClient
import java.util.UUID

/**
 * Класс для взаимодействия с сетевым плагином Flipper.
 * Хранит синглтон-экземпляр сетевого плагина Flipper + позволяет логировать произвольные события в окно "Network"
 */
object FlipperNetworkPluginHelper {

    private val networkFlipperPlugin = NetworkFlipperPlugin()

    /**
     * Зарегистрировать экземпляр сетевого плагина в клиенте
     */
    fun register(flipperClient: FlipperClient) {
        flipperClient.addPlugin(networkFlipperPlugin)
    }

    /**
     * Добавить Flipper-перехватчик для логирования okHttp-запросов
     */
    fun addOkHttpInterceptor(okHttpBuilder: OkHttpClient.Builder) {
        okHttpBuilder.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
    }

    /**
     * Логировать сетевое событие в окно "Network"
     */
    fun logEvent(eventName: String, jsonData: String) {

        val requestId = UUID.randomUUID().toString()
        val timeStamp = System.currentTimeMillis()

        networkFlipperPlugin.reportRequest(
            createRequestInfo(
                eventName = eventName,
                requestId = requestId,
                timeStamp = timeStamp
            )
        )

        networkFlipperPlugin.reportResponse(
            createResponseInfo(
                jsonData = jsonData,
                requestId = requestId,
                timeStamp = timeStamp
            )
        )

    }

    private fun createRequestInfo(
        eventName: String,
        requestId: String,
        timeStamp: Long
    ): RequestInfo {
        val requestInfo = RequestInfo()
        requestInfo.method = "EVENT"
        requestInfo.requestId = requestId
        requestInfo.uri = "event://$eventName"
        requestInfo.timeStamp = timeStamp
        return requestInfo
    }

    private fun createResponseInfo(
        jsonData: String,
        requestId: String,
        timeStamp: Long
    ): ResponseInfo {
        val responseInfo = ResponseInfo()
        responseInfo.requestId = requestId
        responseInfo.statusCode = 200
        responseInfo.timeStamp = timeStamp
        responseInfo.body = jsonData.toByteArray()
        responseInfo.headers.add(Header("Content-Type", "application/json; charset=utf-8"))
        return responseInfo
    }
}