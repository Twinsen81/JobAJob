package jobajob.feature.dashboard.api

import androidx.fragment.app.Fragment
import jobajob.feature.dashboard.presentation.navigation.DashboardNavigationFragment
import javax.inject.Inject

class DashboardApiImpl @Inject constructor(
    private val vacanciesGateway: VacanciesGateway
) : DashboardFeatureApi {
    override fun getDashboardFragment(): Fragment = DashboardNavigationFragment()
    override fun getVacanciesGateway(): VacanciesGateway = vacanciesGateway
}