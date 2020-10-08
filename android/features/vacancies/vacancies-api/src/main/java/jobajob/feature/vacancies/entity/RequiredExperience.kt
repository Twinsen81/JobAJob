package jobajob.feature.vacancies.entity

import java.util.*

/**
 * The minimal experience required for the candidate to apply to the position
 */
enum class RequiredExperience(val experience: String) {

    Whatever("Doesn't matter"),
    NoExperience("No experience"),
    Minimum("1-3 years"),
    Average("3-6 years"),
    Great("More than 6 years"),
    Unknown(""),
    ;

    override fun toString(): String = experience

    companion object {
        fun fromString(type: String): RequiredExperience {
            return values().firstOrNull {
                it.experience.toLowerCase(Locale.ROOT) == type.toLowerCase(Locale.ROOT)
            } ?: Unknown
        }
    }
}

