package jobajob.library.logger.flipper

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkReporter.*
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin.SharedPreferencesDescriptor
import com.facebook.soloader.SoLoader
import jobajob.library.network.logger.NetworkLogger
import okhttp3.OkHttpClient
import java.util.*

/**
 * Логер на базе Flipper.
 * Логирует сетевые события, позволяет получить доступ к БД, shared preferences, view hierarchy
 */
class LoggerFlipper : NetworkLogger {

    private val networkFlipperPlugin = NetworkFlipperPlugin()

    fun init(applicationContext: Context): NetworkLogger {
        SoLoader.init(applicationContext, false)

        val client = AndroidFlipperClient.getInstance(applicationContext)
        client.addPlugin(networkFlipperPlugin)
        client.addPlugin(InspectorFlipperPlugin(applicationContext, DescriptorMapping.withDefaults()))
        client.addPlugin(DatabasesFlipperPlugin(applicationContext))
        client.addPlugin(
            SharedPreferencesFlipperPlugin(
                applicationContext, listOf(
                    SharedPreferencesDescriptor("JobAJob", Context.MODE_PRIVATE)
                )
            )
        )
        client.start()
        return this
    }

    override fun addOkHttpInterceptor(okHttpBuilder: OkHttpClient.Builder) {
        okHttpBuilder.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
    }

    /**
     * Логировать сетевое событие в окно "Network"
     */
    override fun logEvent(eventName: String, jsonData: String) {

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