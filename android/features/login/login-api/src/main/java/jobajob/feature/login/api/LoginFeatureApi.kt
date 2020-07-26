package jobajob.feature.login.api

import android.content.Context
import android.content.Intent

interface LoginFeatureApi {
    fun getLoginScreenIntent(context: Context): Intent
}