package jobajob.feature.dashboard.presentation.vacancies

import jobajob.feature.dashboard.presentation.vacancies.adapter.header.HeaderItem
import jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies.VacancyItem

/**
 * The view state of the vacancies fragment - the data to render in the fragment
 */
internal data class VacanciesViewState(
    val loading: Boolean,
    val header: HeaderItem?,
    val vacancies: List<VacancyItem>
) {
    companion object {
        val emptyLoading = VacanciesViewState(
            loading = true,
            header = null,
            vacancies = emptyList()
        )
    }
}