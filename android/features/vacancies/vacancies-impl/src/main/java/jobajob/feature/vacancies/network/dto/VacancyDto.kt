@file:UseSerializers(DateSerializer::class)

package jobajob.feature.vacancies.network.dto

import jobajob.library.network.serializers.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.util.*

/**
 * Information about a vacancy (an open position at a company)
 */
@Serializable
internal data class VacancyDto(
    /**
     * Id at the backend
     */
    @SerialName("id")
    var id: String? = null,

    /**
     * The open position
     */
    @SerialName("title")
    val title: String,

    /**
     * The city where the position is offered
     */
    @SerialName("city")
    val city: String,

    /**
     * The vacancy description
     */
    @SerialName("description")
    val description: String,

    /**
     * The industry that the vacancy is related to
     */
    @SerialName("field")
    val industry: String,

    /**
     * The date and time when the vacancy was created or last updated
     */
    @SerialName("date")
    val date: Date,

    /**
     * Minimum salary offered
     */
    @SerialName("salary_min")
    val salaryMin: Int? = null,

    /**
     * Maximum salary offered
     */
    @SerialName("salary_max")
    val salaryMax: Int? = null,

    /**
     * The type of the salary offered
     */
    @SerialName("salary_type")
    val salaryType: String? = null,

    /**
     * The employer's Id at the backend
     */
    @SerialName("company_id")
    val employerId: String,

    /**
     * The URL to the employer's logo
     */
    @SerialName("company_logo")
    val employerLogoUrl: String,

    /**
     * The employer's name (business name)
     */
    @SerialName("company_name")
    val employerName: String,

    /**
     * Required work experience
     */
    @SerialName("required_experience")
    val experience: String,

    /**
     * The work schedule of the vacancy
     */
    @SerialName("work_schedule")
    val schedule: String,

    /**
     * A set of skills required/desired to take the position
     */
    @SerialName("required_skills")
    val skills: List<String> = emptyList(),
)
