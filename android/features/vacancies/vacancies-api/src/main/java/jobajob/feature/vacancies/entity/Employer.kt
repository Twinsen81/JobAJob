package jobajob.feature.vacancies.entity

/**
 * A company or a person who offers a job
 */
data class Employer(

    /**
     * Id at the backend
     */
    val id: String,

    /**
     * The official web site address
     */
    val webSiteUrl: String?,

    /**
     * Logo of the company
     */
    val logo: String?,

    /**
     * Images of the office, team, etc - anything related to the job
     */
    val imagesUrls: List<String>,

    /**
     * The location of the office/headquarters
     */
    val location: EmployerLocation,

    /**
     * The contact information
     */
    val contact: EmployerContact,
)
