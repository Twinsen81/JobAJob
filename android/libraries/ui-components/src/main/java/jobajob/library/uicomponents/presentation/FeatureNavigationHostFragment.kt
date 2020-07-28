package jobajob.library.uicomponents.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jobajob.library.uicomponents.R
import jobajob.library.uicomponents.navigation.BackButtonHandler
import jobajob.library.uicomponents.navigation.FeatureNavigator
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * This class should be inherited by the root screen of a feature.
 * It provides independent navigation between the feature's screens (fragment).
 */
abstract class FeatureNavigationHostFragment : BaseNavigationFragment() {

    private val cicerone: Cicerone<Router> = Cicerone.create()
    val featureRouter = cicerone.router
    private val featureNavigator: FeatureNavigator = FeatureNavigator(featureRouter, cicerone.navigatorHolder)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.feature_navigation_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        featureNavigator.setNavigationHost(
            requireActivity(),
            childFragmentManager,
            R.id.feature_navigation_host
        )

        if (savedInstanceState == null)
            featureRouter.navigateTo(getStartScreen())
    }

    /**
     * Provide the screen that will be shown when this feature starts
     */
    abstract fun getStartScreen(): SupportAppScreen

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