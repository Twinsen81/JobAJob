package com.evartem.jobajob.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jobajob.feature.dashboard.di.DaggerDashboardFeatureComponent_FeatureDependenciesComponent
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.feature.favorites.di.DaggerFavoritesFeatureComponent_FeatureDependenciesComponent
import jobajob.feature.favorites.di.FavoritesFeatureComponent
import jobajob.feature.login.di.DaggerLoginFeatureComponent_FeatureDependenciesComponent
import jobajob.feature.login.di.LoginFeatureComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureInjector @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val libraryInjector: LibraryInjector
) {

    fun loginFeatureComponent() =
        LoginFeatureComponent.initAndGet(
            DaggerLoginFeatureComponent_FeatureDependenciesComponent.builder()
                .utilsApi(libraryInjector.utils())
                .build()
        )

    fun dashboardFeatureApi() =
        DashboardFeatureComponent.initAndGet(
            DaggerDashboardFeatureComponent_FeatureDependenciesComponent.builder()
                .utilsApi(libraryInjector.utils())
                .build()
        )

    fun favoritesFeatureApi() =
        FavoritesFeatureComponent.initAndGet(
            DaggerFavoritesFeatureComponent_FeatureDependenciesComponent.builder()
                .utilsApi(libraryInjector.utils())
                .build()
        )
}