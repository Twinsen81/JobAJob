package jobajob.feature.vacancies.entity

/**
 * The location of the employer's office/headquarters
 */
@Serializable
data class EmployerLocation(

    /**
     * The city
     */
    val city: String,

    /**
     * The full address of the location (including the country and the city)
     */
    val address: String,
    /**
     * Latitude, in degrees
     */
    val latitude: Double,

    /**
     * Longitude, in degrees
     */
    val longitude: Double
)
