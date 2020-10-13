package jobajob.feature.vacancies.entity

/**
 * One page of the employers list
 */
data class EmployerPage(
    /**
     * Employers
     */
    val employers: List<Employer>,

    /**
     * Pointer to the next page of the data (page id, record id, etc.)
     */
    val nextStartsAt: String?
)