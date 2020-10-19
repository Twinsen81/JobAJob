package jobajob.feature.vacancies.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The location of the employer's office/headquarters
 */
@Serializable
data class EmployerLocationDto(

    /**
     * The city
     */
    @SerialName("city")
    val city: String,

    /**
     * The full address of the location (including the country and the city)
     */
    @SerialName("address")
    val address: String,

    /**
     * The employer's location: longitude & latitude, in degrees. Format: "54.739261, 55.970812"
     */
    @SerialName("coordinates")
    val coordinates: String,
)
