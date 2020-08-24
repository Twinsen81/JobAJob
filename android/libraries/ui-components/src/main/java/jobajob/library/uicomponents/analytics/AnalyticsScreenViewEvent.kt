package jobajob.library.uicomponents.analytics

import jobajob.library.analytics.AnalyticsEvent
import timber.log.Timber

/**
 * User opened the screen
 */
object AnalyticsScreenViewEvent : AnalyticsEvent() {
    override val name = "screen_view"

    operator fun invoke(screenName: String) {
        Timber.tag(tag).i(screenName)
    }
}