package jobajob.feature.vacancies.network

import jobajob.feature.vacancies.network.dto.PromoVacancyDto
import jobajob.feature.vacancies.network.dto.VacancyDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API to access vacancies
 */
internal interface VacanciesServerApi {

    /**
     * Get all available vacancies
     */
    @GET("/vacancies.json")
    suspend fun getVacancies(): Map<String, VacancyDto>

    /**
     * Get info about one vacancy with id = [vacancyId]
     */
    @GET("/vacancies/{id}.json")
    suspend fun getVacancy(@Path("id") vacancyId: String): VacancyDto

    /**
     * Get only promoted (featured) vacancies
     */
    @GET("/promo_vacancies.json")
    suspend fun getPromoVacancies(): Map<String, PromoVacancyDto>
}