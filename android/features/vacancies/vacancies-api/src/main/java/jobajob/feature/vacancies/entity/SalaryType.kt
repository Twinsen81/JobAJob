package jobajob.feature.vacancies.entity

import java.util.*

/**
 * The type of salary that the employer specified in the vacancy
 */
enum class SalaryType(val type: String) {

    /**
     * The salary is the take-home payment after all deductions (tax, pension, etc.)
     */
    Net("net"),

    /**
     * The salary before all deductions (tax, pension, etc.)
     */
    Gross("gross"),

    Unknown(""),
    ;

    override fun toString(): String = type

    companion object {
        fun fromString(type: String?): SalaryType {
            return values().firstOrNull {
                it.type.toLowerCase(Locale.ROOT) == type?.toLowerCase(Locale.ROOT)
            } ?: Unknown
        }
    }
}
