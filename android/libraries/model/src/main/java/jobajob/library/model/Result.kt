package jobajob.library.model

/**
 * A class representing the result of an operation (network request, DB request, etc.)
 * The result can be either "success" or "error" but not the both at the same time.
 */
sealed class Result<out A : Any, out B : Any> {
    data class Success<out A : Any>(val value: A) : Result<A, Nothing>()
    data class Error<out B : Any>(val error: B) : Result<Nothing, B>()

    val isSuccess get() = this is Success<A>
    val isError get() = this is Error<B>

    fun either(onSuccessDo: (A) -> Any, onErrorDo: (B) -> Any): Any =
        when (this) {
            is Success -> onSuccessDo(value)
            is Error -> onErrorDo(error)
        }
}