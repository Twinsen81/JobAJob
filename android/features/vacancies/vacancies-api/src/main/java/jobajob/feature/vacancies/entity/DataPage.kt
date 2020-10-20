package jobajob.feature.vacancies.entity

/**
 * One page of the data (for pagination)
 */
data class DataPage<T>(
    /**
     * The data
     */
    val data: List<T>,

    /**
     * Pointer to the next page of the data (page id, record id, etc.)
     */
    val nextStartsAt: String?
)