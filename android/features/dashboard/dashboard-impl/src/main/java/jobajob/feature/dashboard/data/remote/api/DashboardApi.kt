package jobajob.feature.dashboard.data.remote.api

import io.reactivex.Single
import jobajob.feature.dashboard.data.remote.dto.VacanciesRemoteDto
import retrofit2.Response
import retrofit2.http.GET

internal interface DashboardApi {

    @GET("vacancies")
    fun fetchVacancies() : Single<Response<VacanciesRemoteDto>>
}