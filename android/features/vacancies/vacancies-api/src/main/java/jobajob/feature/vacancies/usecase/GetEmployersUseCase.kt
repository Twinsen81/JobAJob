package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.entity.VacanciesPage
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result

interface GetEmployersUseCase {
    suspend fun getEmployers(startAt: String?, number: Int?): EmployersPageResult
}

typealias EmployersPageResult = Result<VacanciesPage, Failure>