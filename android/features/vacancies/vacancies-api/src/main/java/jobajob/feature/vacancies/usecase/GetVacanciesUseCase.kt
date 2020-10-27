package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.entity.DataPage
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result

interface GetVacanciesUseCase {
    /**
     * Get all available vacancies
     */
    suspend fun getVacancies(startAt: String?, number: Int?): VacanciesPageResult

    /**
     * Get info about one vacancy with id = [vacancyId]
     */
    suspend fun getVacancy(vacancyId: String): VacancyResult
}

typealias VacanciesPageResult = Result<DataPage<Vacancy>, Failure>

typealias VacancyResult = Result<Vacancy, Failure>