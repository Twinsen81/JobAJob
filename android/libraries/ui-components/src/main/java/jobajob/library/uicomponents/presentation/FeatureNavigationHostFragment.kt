package jobajob.library.uicomponents.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jobajob.library.uicomponents.R
import jobajob.library.uicomponents.navigation.BackButtonHandler
import jobajob.library.uicomponents.navigation.FeatureNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

/**
 * This class should be inherited by the root screen of a feature.
 * It provides independent navigation between the feature's screens (fragment).
 */
abstract class FeatureNavigationHostFragment : BaseNavigationFragment() {

    @Inject
    lateinit var featureNavigator: FeatureNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.feature_navigation_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectDependencies()

        featureNavigator.setNavigationHost(
            activity!!,
            childFragmentManager,
            R.id.feature_navigation_host
        )

        if (savedInstanceState == null)
            featureRouter.navigateTo(getStartScreen())
    }

    /**
     * Perform injection into this fragment, e.g.:
     *
     * Component.inject(this)
     */
    abstract fun injectDependencies()

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