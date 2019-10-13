package jobajob.feature.dashboard.data.remote.api

import io.reactivex.Single
import jobajob.feature.dashboard.data.remote.dto.VacanciesRemoteDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface DashboardApi {

    @GET("vacancies")
    fun fetchVacancies(@Query("page") page: Int) : Single<Response<VacanciesRemoteDto>>
}