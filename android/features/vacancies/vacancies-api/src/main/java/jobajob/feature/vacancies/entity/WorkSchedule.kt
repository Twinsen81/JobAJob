package jobajob.feature.vacancies.entity

import java.util.*

/**
 * The work schedule of the vacancy
 */
enum class WorkSchedule(val workSchedule: String) {

    FullDay("Full day"),
    Flexible("Flexible"),
    Remote("Remote"),
    Unknown(""),
    ;

    override fun toString(): String = workSchedule

    companion object {
        fun fromString(type: String): WorkSchedule {
            return values().firstOrNull {
                it.workSchedule.toLowerCase(Locale.ROOT) == type.toLowerCase(Locale.ROOT)
            } ?: Unknown
        }
    }
}
