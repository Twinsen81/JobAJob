package jobajob.feature.dashboard.navigation

import androidx.fragment.app.Fragment
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.feature.dashboard.presentation.vacancies.VacanciesFragment
import jobajob.library.uicomponents.presentation.FeatureNavigationHostFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

internal class DashboardNavigationFragment : FeatureNavigationHostFragment() {

    override fun injectDependencies() =
        DashboardFeatureComponent.get().inject(this)


    override fun getStartScreen(): SupportAppScreen =
        object : SupportAppScreen() {
            override fun getFragment(): Fragment =
                VacanciesFragment()
        }
}