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
     * The company's name (business name)
     */
    val name: String,

    /**
     * The official web site address
     */
    val webSiteUrl: String?,

    /**
     * Logo of the company
     */
    val logoUrl: String?,

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
