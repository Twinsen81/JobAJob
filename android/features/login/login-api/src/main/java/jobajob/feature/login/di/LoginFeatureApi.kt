package jobajob.feature.login.di

import android.content.Context
import android.content.Intent

interface LoginFeatureApi {
    fun getLoginScreenIntent(context: Context): Intent
}