package com.evartem.jobajob.logging

import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun isLoggable(tag: String?, priority: Int) =
        priority > android.util.Log.INFO

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        crashlytics.log("${tag.orEmpty()} $message")

        if (t != null && priority == android.util.Log.ERROR) {
            crashlytics.recordException(t)
        }
    }
}