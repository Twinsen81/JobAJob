package jobajob.library.session

/**
 * Information about the currently logged in user
 */
sealed class AuthenticationData {

    /**
     * The user hasn't logged in yet
     */
    object none : AuthenticationData()

    /**
     * The login attempt failed
     */
    object error : AuthenticationData()

    /**
     * The logged in user's info
     */
    data class User(
        /**
         * Unique identifier of the user
         */
        val uid: String,

        /**
         * Authentication token that can be used to access resources on the server on behalf of the user
         */
        val authToken: String?,

        /**
         * First/second/nick name to show in the UI
         */
        val displayName: String?,

        /**
         * Avatar URL
         */
        val photoUrl: String?,

        /**
         * E-mail address
         */
        val email: String?,

        /**
         * Is the user using a guest account? Anonymous users can later sign-in without losing
         * the created context (settings, cart contents, etc.)
         */
        val isAnonymous: Boolean
    ) : AuthenticationData()

}