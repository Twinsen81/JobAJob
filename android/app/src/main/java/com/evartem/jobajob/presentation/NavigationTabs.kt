package com.evartem.jobajob.presentation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.evartem.jobajob.R
import com.evartem.jobajob.di.FeatureInjector
import javax.inject.Inject

class NavigationTabs @Inject constructor(featureInjector: FeatureInjector) {

    private val tabs = mapOf(
        NavigationTab.DASHBOARD to
                Tab(R.id.navigation_dashboard) {
                    featureInjector.dashboardFeatureApi().getDashboardFragment()
                },
        NavigationTab.FAVORITES to
                Tab(R.id.navigation_favorites) {
                    featureInjector.favoritesFeatureApi().getFavoritesFragment()
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