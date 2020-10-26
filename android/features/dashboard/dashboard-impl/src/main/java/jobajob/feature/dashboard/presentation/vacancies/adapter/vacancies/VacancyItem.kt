package jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies

/**
 * UI model of a vacancy in the vacancies list
 */
internal data class VacancyItem(
    val id: String,
    val title: String,
    val city: String,
    val salary: String?,
    val employerName: String,
    val isFavorite: Boolean,
)