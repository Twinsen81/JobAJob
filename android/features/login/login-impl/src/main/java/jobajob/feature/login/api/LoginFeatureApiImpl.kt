package jobajob.feature.login.api

import android.content.Context
import android.content.Intent
import jobajob.feature.login.presentation.LoginActivity

object LoginFeatureApiImpl: LoginFeatureApi {
    override fun getLoginScreenIntent(context: Context): Intent = LoginActivity.createIntent(context)
}