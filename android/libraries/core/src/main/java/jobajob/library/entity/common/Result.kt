package jobajob.library.entity.common

/**
 * A class representing the result of an operation (network request, DB request, etc.)
 * The result can be either "success" or "error" but not the both at the same time.
 */
sealed class Result<out S : Any, out E : Any> {
    data class Success<out S : Any>(val value: S) : Result<S, Nothing>()
    data class Error<out E : Any>(val error: E) : Result<Nothing, E>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>

    fun either(onSuccessDo: (S) -> Unit, onErrorDo: (E) -> Unit): Unit =
        when (this) {
            is Success -> onSuccessDo(value)
            is Error -> onErrorDo(error)
        }
}