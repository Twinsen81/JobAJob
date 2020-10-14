package jobajob.feature.vacancies.network

import jobajob.library.session.AuthenticationData
import jobajob.library.session.Session
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Authentication interceptor for Firebase.
 * Adds the authentication parameter (ID token) to the URL
 */
@ExperimentalCoroutinesApi
internal class FirebaseAuthInterceptor(private val session: Session) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authToken = firebaseIdToken

        if (authToken.isNullOrEmpty()) return chain.proceed(chain.request())

        val originalUrl = originalRequest.url
        val authUrl = originalUrl.newBuilder()
            .addQueryParameter("auth", authToken)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(authUrl)
            .build()

        return chain.proceed(newRequest)
    }

    private val firebaseIdToken: String?
        get() = (session.authData as? AuthenticationData.User)?.authToken
}