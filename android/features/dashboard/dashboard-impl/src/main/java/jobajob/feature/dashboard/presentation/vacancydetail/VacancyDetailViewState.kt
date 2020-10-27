package jobajob.feature.dashboard.presentation.vacancydetail

import jobajob.feature.vacancies.entity.Vacancy

/**
 * The view state of the vacancy fragment - the data to render in the fragment
 */
internal sealed class VacancyDetailViewState {

    /**
     * Loading data
     */
    object Loading : VacancyDetailViewState()

    /**
     * An error occurred while loading data
     */
    data class Error(val text: String?) : VacancyDetailViewState()

    /**
     * The data - the complete info about the requested vacancy
     */
    data class Data(val vacancy: Vacancy) : VacancyDetailViewState()
}