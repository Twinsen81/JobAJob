package jobajob.feature.login.di

interface LoginFeatureApi {
    fun getLoginScreenIntent(email: String = "")
}