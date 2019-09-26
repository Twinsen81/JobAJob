package com.evartem.jobajob.di

import android.app.Application
import jobajob.feature.login.di.DaggerLoginFeatureComponent_LoginFeatureDependenciesComponent
import jobajob.feature.login.di.LoginFeatureApi
import jobajob.feature.login.di.LoginFeatureComponent
import jobajob.library.utils.di.UtilsComponent
import javax.inject.Inject

class FeatureInjector @Inject constructor(private val application: Application) {

    fun loginFeatureComponent() =
        LoginFeatureComponent.initAndGet(
            DaggerLoginFeatureComponent_LoginFeatureDependenciesComponent.builder()
                .utilsApi(UtilsComponent.instance)
                .build()
        )

    fun loginFeatureApi(): LoginFeatureApi = loginFeatureComponent()
}