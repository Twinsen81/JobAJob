package com.evartem.jobajob.logging

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import jobajob.library.analytics.AnalyticsEvent
import jobajob.library.uicomponents.analytics.AnalyticsScreenViewEvent
import jobajob.library.uicomponents.analytics.AnalyticsViewVacancyEvent
import timber.log.Timber

class AnalyticsTree(appContext: Context) : Timber.Tree() {

    private val analytics = FirebaseAnalytics.getInstance(appContext)

    override fun isLoggable(tag: String?, priority: Int) = AnalyticsEvent.isAnalyticsTag(tag)

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (tag) {
            AnalyticsScreenViewEvent.tag -> logScreenView(message)
            AnalyticsViewVacancyEvent.tag -> logItemView(message)
        }
    }

    private fun logScreenView(screenName: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,
            Bundle().apply { putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName) })
    }

    private fun logItemView(itemId: String) {
        analytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM,
            Bundle().apply { putString(FirebaseAnalytics.Param.ITEM_ID, itemId) })
    }
}