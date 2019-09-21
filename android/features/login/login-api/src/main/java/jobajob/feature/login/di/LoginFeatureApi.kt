package jobajob.feature.login.di

import jobajob.feature.login.domain.SessionInfo

interface LoginFeatureApi {
    fun getSession(): SessionInfo
}