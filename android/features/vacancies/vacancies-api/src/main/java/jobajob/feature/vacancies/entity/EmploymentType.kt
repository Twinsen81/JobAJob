package jobajob.feature.vacancies.entity

import java.util.*

/**
 * How much time the employer expects the employee to work for it
 */
enum class EmploymentType(val employmentType: String) {

    FullTime("Full time"),
    PartTime("Part time"),
    Temporary("Temporary"),
    Unknown(""),
    ;

    override fun toString(): String = employmentType

    companion object {
        fun fromString(type: String): EmploymentType {
            return values().firstOrNull {
                it.employmentType.toLowerCase(Locale.ROOT) == type.toLowerCase(Locale.ROOT)
            } ?: Unknown
        }
    }
}
