package jobajob.feature.vacancies.api

import jobajob.feature.vacancies.usecase.GetVacanciesUseCase

interface VacanciesFeatureApi {
    fun getVacanciesUseCase(): GetVacanciesUseCase
}