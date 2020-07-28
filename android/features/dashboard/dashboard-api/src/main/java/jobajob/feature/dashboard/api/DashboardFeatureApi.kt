package jobajob.feature.dashboard.api

import androidx.fragment.app.Fragment

interface DashboardFeatureApi {
    fun getDashboardFragment(): Fragment
    fun getVacanciesGateway(): VacanciesGateway
}