package jobajob.feature.vacancies.entity

/**
 * One page of the vacancies list
 */
data class VacanciesPage(
    /**
     * Vacancies
     */
    val vacancies: List<Vacancy>,

    /**
     * Pointer to the next page of the data (page id, record id, etc.)
     */
    val nextStartsAt: String?
)