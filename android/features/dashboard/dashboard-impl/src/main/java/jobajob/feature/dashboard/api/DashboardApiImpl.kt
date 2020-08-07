package jobajob.feature.dashboard.api

import androidx.fragment.app.Fragment
import jobajob.feature.dashboard.presentation.vacancies.VacanciesFragment
import javax.inject.Inject

class DashboardApiImpl @Inject constructor(
    private val vacanciesGateway: VacanciesGateway
) : DashboardFeatureApi {
    override fun getDashboardFragment(): Fragment = VacanciesFragment()
    override fun getVacanciesGateway(): VacanciesGateway = vacanciesGateway
}