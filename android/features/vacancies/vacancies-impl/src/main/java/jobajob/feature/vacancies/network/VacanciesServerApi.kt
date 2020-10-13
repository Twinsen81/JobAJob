package jobajob.feature.vacancies.network

import jobajob.feature.vacancies.entity.Employer
import jobajob.feature.vacancies.entity.Vacancy
import retrofit2.Response
import retrofit2.http.GET

internal interface VacanciesServerApi {

    @GET("/companies.json")
    suspend fun getCompanies(): Response<Map<String, Employer>>


    @GET("/vacancies.json")
    suspend fun getVacancies(): Response<Map<String, Vacancy>>
}