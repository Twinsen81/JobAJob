package jobajob.library.session

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
interface Session {
    val authData: AuthenticationData

    val authDataFlow: StateFlow<AuthenticationData>
}