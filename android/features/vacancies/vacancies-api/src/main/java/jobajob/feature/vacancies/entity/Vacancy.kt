package jobajob.feature.vacancies.entity

import java.util.*

/**
 * Information about a vacancy (an open position at a company)
 */
data class Vacancy(
    /**
     * Id at the backend
     */
    val id: String,

    /**
     * The open position
     */
    val title: String,

    /**
     * The vacancy description
     */
    val description: String,

    /**
     * The city where the position is offered
     */
    val city: String,

    /**
     * The industry that the vacancy is related to
     */
    val industry: Industry,

    /**
     * The date and time when the vacancy was created or last updated
     */
    val date: Date,

    /**
     * Minimum salary offered
     */
    val salaryMin: Int?,

    /**
     * Maximum salary offered
     */
    val salaryMax: Int?,

    /**
     * The type of the salary offered
     */
    val salaryType: SalaryType?,

    /**
     * Short info about the employer
     */
    val employer: EmployerShort,

    /**
     * Required work experience
     */
    val experience: RequiredExperience,

    /**
     * The work schedule of the vacancy
     */
    val schedule: WorkSchedule,

    /**
     * A set of skills required/desired to take the position
     */
    val skills: List<String>,
)
