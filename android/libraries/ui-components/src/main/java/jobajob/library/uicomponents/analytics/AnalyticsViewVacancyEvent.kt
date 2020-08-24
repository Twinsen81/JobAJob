package jobajob.library.uicomponents.analytics

import jobajob.library.analytics.AnalyticsEvent
import timber.log.Timber

/**
 * User viewed the vacancy
 */
object AnalyticsViewVacancyEvent : AnalyticsEvent() {
    override val name = "view_item"

    operator fun invoke(itemId: String) {
        Timber.tag(tag).i(itemId)
    }
}