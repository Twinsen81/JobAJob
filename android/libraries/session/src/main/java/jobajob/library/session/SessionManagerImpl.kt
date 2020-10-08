package jobajob.library.session

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class SessionManagerImpl : SessionManager {

    private val _authDataFlow = MutableStateFlow<AuthenticationData>(AuthenticationData.none)

    override val authDataFlow: StateFlow<AuthenticationData>
        get() = _authDataFlow

    override val authData: AuthenticationData
        get() = _authDataFlow.value

    override fun setAuthenticationData(data: AuthenticationData) {
        _authDataFlow.value = data
    }
}