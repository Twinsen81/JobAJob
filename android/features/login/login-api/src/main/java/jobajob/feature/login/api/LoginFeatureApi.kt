package jobajob.feature.login.api

import androidx.fragment.app.FragmentActivity

interface LoginFeatureApi {
    fun login(activity: FragmentActivity)
    fun logout(activity: FragmentActivity)
}