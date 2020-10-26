package jobajob.feature.dashboard.presentation.vacancies

/**
 * One-time events sent from the View to the ViewModel
 */
internal sealed class VacanciesViewAction {

    /**
     * A vacancy clicked in the list of vacancies
     */
    data class VacancyClicked(val vacancyId: String) : VacanciesViewAction()
}