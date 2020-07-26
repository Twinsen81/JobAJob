package com.evartem.jobajob.presentation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.evartem.jobajob.R
import jobajob.feature.dashboard.api.DashboardFeatureApi
import jobajob.feature.favorites.api.FavoritesFeatureApi
import javax.inject.Inject

class NavigationTabs @Inject constructor(private val dashboardFeatureApi: DashboardFeatureApi,
                                         private val favoritesFeatureApi: FavoritesFeatureApi) {

    private val tabs = mapOf(
        NavigationTab.DASHBOARD to
                Tab(R.id.navigation_dashboard) {
                    dashboardFeatureApi.getDashboardFragment()
                },
        NavigationTab.FAVORITES to
                Tab(R.id.navigation_favorites) {
                    favoritesFeatureApi.getFavoritesFragment()
                },
        NavigationTab.RESUMES to
                Tab(R.id.navigation_resumes) {
                    StubFragment.newInstance("RESUMES")
                },
        NavigationTab.MORE to
                Tab(R.id.navigation_more) {
                    StubFragment.newInstance("MORE...")
                }
    )

    fun getFragmentForTab(tab: NavigationTab) = tabs.getValue(tab).fragment

    fun getMenuItemIdForTab(tab: NavigationTab) = tabs.getValue(tab).menuItemId

    fun getTabForMenuItemId(@IdRes menuItemId: Int) =  tabs.entries.first { it.value.menuItemId == menuItemId }.key

    private class Tab(@IdRes val menuItemId: Int, fragmentCreator: () -> Fragment) {
        val fragment = fragmentCreator()
    }
}