package jobajob.library.session

import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Implemented by a class who knows how to login/logout users and set other session data
 */
@ExperimentalCoroutinesApi
interface SessionManager : Session {

    /**
     * Set new authentication data (usually called after login/logout)
     */
    fun setAuthenticationData(data: AuthenticationData)
}