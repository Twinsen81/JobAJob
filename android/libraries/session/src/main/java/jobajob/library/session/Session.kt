package jobajob.library.session

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

/**
 * Data, associated with the currently running instance of the app (the logged in user, etc.)
 */
@ExperimentalCoroutinesApi
interface Session {
    /**
     * Get the current authentication data
     */
    val authData: AuthenticationData

    /**
     * Subscribe to the authentication data stream.
     * The subscriber will be notified every time the authentication data is changed
     */
    val authDataFlow: StateFlow<AuthenticationData>
}