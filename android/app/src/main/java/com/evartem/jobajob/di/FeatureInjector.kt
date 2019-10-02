package com.evartem.jobajob.di

import android.app.Application
import jobajob.feature.dashboard.di.DaggerDashboardFeatureComponent_FeatureDependenciesComponent
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.feature.login.di.DaggerLoginFeatureComponent_LoginFeatureDependenciesComponent
import jobajob.feature.login.di.LoginFeatureComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureInjector @Inject constructor(private val application: Application, private val libraryInjector: LibraryInjector) {

    fun loginFeatureComponent() =
        LoginFeatureComponent.initAndGet(
            DaggerLoginFeatureComponent_LoginFeatureDependenciesComponent.builder()
                .utilsApi(libraryInjector.utils())
                .build()
        )

    fun dashboardFeatureComponent() =
        DashboardFeatureComponent.initAndGet(
            DaggerDashboardFeatureComponent_FeatureDependenciesComponent.builder()
                .utilsApi(libraryInjector.utils())
                .build()
        )
}