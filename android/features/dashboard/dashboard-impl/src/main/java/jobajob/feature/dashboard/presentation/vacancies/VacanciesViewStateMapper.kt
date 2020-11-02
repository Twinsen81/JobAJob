package jobajob.feature.dashboard.presentation.vacancies

import jobajob.feature.dashboard.presentation.vacancies.adapter.header.HeaderItem
import jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies.VacancyItem
import jobajob.feature.vacancies.entity.SalaryType
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.library.entity.common.Failure
import javax.inject.Inject

internal class VacanciesViewStateMapper @Inject constructor() {
    fun map(prevState: VacanciesViewState, vacancies: List<Vacancy>): VacanciesViewState {
        return VacanciesViewState(
            loading = false,
            header = HeaderItem("Vacancies"),
            vacancies = vacancies.map {
                VacancyItem(
                    id = it.id,
                    title = it.title,
                    city = it.city,
                    salary = getVacancySalary(it.salaryType, it.salaryMin, it.salaryMax),
                    employerName = it.employer.name,
                    isFavorite = false
                )
            }
        )
    }

    fun map(prevState: VacanciesViewState, isLoading: Boolean = true): VacanciesViewState {
        return prevState.copy(loading = isLoading)
    }

    fun map(prevState: VacanciesViewState, error: Failure): VacanciesViewState {
        return prevState.copy(loading = false)
    }

    private fun getVacancySalary(salaryType: SalaryType?, salaryMin: Int?, salaryMax: Int?): String? {
        return when {
            (salaryType == null || (salaryMin == null && salaryMax == null)) -> null
            salaryMin == null -> "up to $salaryMax"
            salaryMax == null -> "from $salaryMin"
            else -> "$salaryMin - $salaryMax (${salaryType})"
        }
    }
}