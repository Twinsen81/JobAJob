package jobajob.library.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

data class AuthInterceptor(
    private val method: String,
    private val accessToken: String
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "$method $accessToken")
            .build()
        return chain.proceed(request)
    }

    override fun toString() = "AuthInterceptor(accessToken='$accessToken', method='$method')"
}