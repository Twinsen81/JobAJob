package jobajob.feature.vacancies.network

import jobajob.feature.vacancies.network.dto.VacancyDto
import retrofit2.http.GET

internal interface VacanciesServerApi {
    @GET("/vacancies.json")
    suspend fun getVacancies(): Map<String, VacancyDto>
}