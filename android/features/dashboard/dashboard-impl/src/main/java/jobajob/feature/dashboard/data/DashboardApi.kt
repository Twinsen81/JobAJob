package jobajob.feature.dashboard.data

import io.reactivex.Single
import jobajob.library.model.SpringRestResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface DashboardApi {

    @GET("vacancies")
    fun fetchVacancies() : Single<Response<SpringRestResponse>>
}