package jobajob.feature.vacancies.entity

/**
 * A promoted vacancy (paid promotion)
 */
data class PromoVacancy(

    /**
     * Id at the backend
     */
    val id: String,

    /**
     * The open position
     */
    val title: String,

    /**
     * The employer who offers the vacancy
     */
    val employer: EmployerShort,
)