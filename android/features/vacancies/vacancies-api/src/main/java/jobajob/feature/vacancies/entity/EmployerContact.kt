package jobajob.feature.vacancies.entity

/**
 * The contact information of the employer's HR
 */
data class EmployerContact(
    /**
     * First or/and second name
     */
    val name: String,

    /**
     * E-mail
     */
    val email: String,

    /**
     * Phone number
     */
    val phone: String?,
)
