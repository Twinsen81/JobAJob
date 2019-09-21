package com.evartem.jobajob.di

import jobajob.feature.login.di.DaggerLoginFeatureComponent_LoginFeatureDependenciesComponent
import jobajob.feature.login.di.LoginFeatureComponent
import jobajob.library.utils.di.UtilsComponent

object FeatureInjector {

    fun loginFeature() =
        LoginFeatureComponent.initAndGet(
            DaggerLoginFeatureComponent_LoginFeatureDependenciesComponent.builder()
                .utilsApi(UtilsComponent.instance)
                .build()
        )
}