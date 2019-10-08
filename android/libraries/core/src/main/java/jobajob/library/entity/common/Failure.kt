package jobajob.library.entity.common

/**
 * A class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    /**
     * Connection-specific errors, e.g. no internet connection
     */
    class NetworkConnection(val exception: Throwable? = null, val message: String? = null) : Failure()

    /**
     * Server-specific errors, e.g. 500 Internal Server Error
     */
    class ServerError(val exception: Throwable? = null, val message: String? = null) : Failure()

    /**
     * Unexpected exceptions - errors in code
     */
    class ApplicationError(val exception: Throwable) : Failure()

    /** Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}