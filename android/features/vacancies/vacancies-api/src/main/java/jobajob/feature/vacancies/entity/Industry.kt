package jobajob.feature.vacancies.entity

import java.util.*

/**
 * The industry that the employer is related to
 */
enum class Industry(val industry: String) {

    Finance("Finance"),
    IT("IT"),
    Art("Art"),
    Construction("Construction"),
    Manufacturing("Manufacturing"),
    Science("Science"),
    Education("Education"),
    Unknown(""),
    ;

    override fun toString(): String = industry

    companion object {
        fun fromString(type: String): Industry {
            return values().firstOrNull {
                it.industry.toLowerCase(Locale.ROOT) == type.toLowerCase(Locale.ROOT)
            } ?: Unknown
        }
    }
}
