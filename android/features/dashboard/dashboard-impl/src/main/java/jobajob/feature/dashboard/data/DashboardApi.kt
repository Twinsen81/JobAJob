package jobajob.feature.dashboard.data

import jobajob.library.model.SpringRestResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

internal interface DashboardApi {

    @GET("vacancies")
    fun fetchVacancies() : Call<Response<SpringRestResponse>>
}