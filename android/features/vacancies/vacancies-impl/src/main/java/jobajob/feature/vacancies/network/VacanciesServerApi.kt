package jobajob.feature.vacancies.network

import jobajob.feature.vacancies.usecase.dto.VacancyDto
import retrofit2.http.GET

internal interface VacanciesServerApi {
    @GET("/vacancies.json")
    suspend fun getVacancies(): Map<String, VacancyDto>
}