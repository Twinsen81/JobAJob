package jobajob.feature.dashboard.api

import androidx.fragment.app.Fragment
import jobajob.feature.dashboard.presentation.navigation.DashboardNavigationFragment

object DashboardApiImpl: DashboardFeatureApi {
    override fun getDashboardFragment(): Fragment = DashboardNavigationFragment()
}