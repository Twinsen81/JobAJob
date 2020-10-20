package jobajob.feature.vacancies.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A promoted vacancy (paid promotion)
 */
@Serializable
data class PromoVacancyDto(

    /**
     * Id at the backend
     */
    @SerialName("id")
    var id: String? = null,

    /**
     * The open position
     */
    val title: String,

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
)