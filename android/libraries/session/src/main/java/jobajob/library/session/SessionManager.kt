package jobajob.library.session

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface SessionManager : Session {
    fun setAuthenticationData(data: AuthenticationData)
}