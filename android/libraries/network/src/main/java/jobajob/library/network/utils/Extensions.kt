package jobajob.library.network.utils

import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import retrofit2.Response

fun <T : Any> Response<T>.toResult(): Result<T, Failure> =
    when {
        isSuccessful && body() != null -> Result.Success(body()!!)
        code() in 500 until 600 -> Result.Error(Failure.ServerError(message = errorDescription()))
        code() == 401 -> Result.Error(Failure.Unauthorized(message = errorDescription()))
        else -> Result.Error(Failure.NetworkConnection(message = errorDescription()))
    }

internal fun <T : Any> Response<T>.errorDescription() = "[${code()}]:[${body()}]:[${message()}]"