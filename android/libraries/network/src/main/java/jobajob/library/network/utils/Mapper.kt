package jobajob.library.network.utils

/**
 * Converting one type into another (mapping)
 */
interface Mapper<I, O> {
    /**
     * Convert [input] from type [I] into [O]
     */
    fun map(input: I): O
}