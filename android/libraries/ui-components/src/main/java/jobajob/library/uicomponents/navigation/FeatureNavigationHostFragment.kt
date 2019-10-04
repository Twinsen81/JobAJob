package jobajob.library.uicomponents.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jobajob.library.uicomponents.R
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

abstract class FeatureNavigationHostFragment : BaseFeatureFragment() {

    @Inject
    lateinit var featureNavigator: FeatureNavigator
    @Inject
    lateinit var featureRouter: Router

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

    abstract fun injectDependencies()

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