package jobajob.feature.dashboard.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.dashboard.R
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.feature.dashboard.presentation.vacancies.VacanciesFragment
import jobajob.library.uicomponents.navigation.BackButtonHandler
import jobajob.library.uicomponents.navigation.FeatureNavigator
import jobajob.library.uicomponents.navigation.BaseFeatureFragment
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

internal class DashboardFragment : BaseFeatureFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DashboardViewModel

    @Inject
    lateinit var featureNavigator: FeatureNavigator
    @Inject
    lateinit var featureRouter: Router

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_container, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DashboardFeatureComponent.get().inject(this)

        featureNavigator.setNavigationHost(
            activity!!,
            childFragmentManager,
            R.id.feature_navigation_host
        )

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        if (savedInstanceState == null)
            featureRouter.navigateTo(object : SupportAppScreen() {
                override fun getFragment(): Fragment = VacanciesFragment()
            })
    }

    override fun onResume() {
        super.onResume()

        featureNavigator.startNavigator()
    }

    override fun onPause() {
        super.onPause()

        featureNavigator.pauseNavigator()
    }

    override fun onBackPressed(): Boolean {
        if (childFragmentManager.backStackEntryCount <= 1)
            return false

        val topFragment = childFragmentManager.fragments.last()
        if (topFragment is BackButtonHandler && topFragment.onBackPressed())
            return true

        featureRouter.exit()
        return true
    }

}