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
}

typealias VacanciesPageResult = Result<DataPage<Vacancy>, Failure>