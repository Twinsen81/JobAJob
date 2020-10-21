package com.evartem.jobajob

import android.app.Application
import com.evartem.jobajob.logging.AnalyticsTree
import com.evartem.jobajob.logging.CrashlyticsTree
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class JobajobApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        initFirebaseServices()
        initLogging()
    }

    private fun initFirebaseServices() {

        FirebaseApp.initializeApp(this)

        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setCrashlyticsCollectionEnabled(true)

        val installerPackageName = packageManager.getInstallerPackageName(this.packageName)

        if (installerPackageName != null && installerPackageName.isNotEmpty()) {
            crashlytics.setCustomKey("InstallerPackage", installerPackageName)
        }
    }

    private fun initLogging() {
        Timber.plant(Timber.DebugTree())
        Timber.plant(CrashlyticsTree())
        Timber.plant(AnalyticsTree(this))
    }
}