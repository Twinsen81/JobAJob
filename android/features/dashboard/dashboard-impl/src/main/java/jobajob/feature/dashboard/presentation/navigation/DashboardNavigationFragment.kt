package jobajob.feature.dashboard.presentation.navigation

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.presentation.vacancies.VacanciesFragment
import jobajob.library.uicomponents.presentation.FeatureNavigationHostFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

@AndroidEntryPoint
internal class DashboardNavigationFragment : FeatureNavigationHostFragment() {

    override fun getStartScreen(): SupportAppScreen =
        object : SupportAppScreen() {
            override fun getFragment(): Fragment =
                VacanciesFragment()
        }
}