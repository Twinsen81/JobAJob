package jobajob.library.analytics

/**
 * A base class for all analytics events.
 * Allows analytics events reporting through tagged logging
 */
abstract class AnalyticsEvent {
    companion object {
        private const val tagPrefix = "#Analytics#"

        /**
         * Should a logging event with the tag [loggingTag] be sent to analytics consumer?
         */
        fun isAnalyticsTag(loggingTag: String?): Boolean = loggingTag?.startsWith(tagPrefix) ?: false
    }

    abstract val name: String
    val tag get() = "$tagPrefix$name"
}