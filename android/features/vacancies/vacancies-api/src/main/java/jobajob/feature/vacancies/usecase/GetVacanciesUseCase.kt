package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.entity.VacanciesPage
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result

interface GetVacanciesUseCase {
    suspend fun getVacancies(startAt: String?, number: Int?): VacanciesPageResult
}

typealias VacanciesPageResult = Result<VacanciesPage, Failure>