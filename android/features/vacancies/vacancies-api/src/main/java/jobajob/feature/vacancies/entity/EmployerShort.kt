package jobajob.feature.vacancies.entity

/**
 * Basic info about a company
 */
data class EmployerShort(
    /**
     * Id at the backend
     */
    val id: String,

    /**
     * The company's name (business name)
     */
    val name: String,

    /**
     * The URL to the company's logo
     */
    val logoUrl: String?,
)