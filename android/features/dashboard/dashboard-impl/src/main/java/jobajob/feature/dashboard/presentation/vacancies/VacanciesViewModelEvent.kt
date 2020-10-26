package jobajob.feature.dashboard.presentation.vacancies

/**
 * One-time events sent form the ViewModel to the View (navigation, Snack bar, Toast, etc.)
 */
internal sealed class VacanciesViewModelEvent {

    /**
     * Display a message (with optional [text]) about the error
     */
    data class DisplayError(val text: String = "") : VacanciesViewModelEvent()

    /**
     * Display details of the vacancy with id = [vacancyId]
     */
    data class DisplayVacancy(val vacancyId: String) : VacanciesViewModelEvent()
}