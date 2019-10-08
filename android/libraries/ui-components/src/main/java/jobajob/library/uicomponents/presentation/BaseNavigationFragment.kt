package jobajob.library.uicomponents.presentation

import android.os.Bundle
import android.view.View
import jobajob.library.uicomponents.navigation.BackButtonHandler
import jobajob.library.uicomponents.navigation.RootNavigator
import jobajob.library.uicomponents.widget.Toolbar
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * A fragment of a feature that can navigate to other fragments and
 * can handle the hardware BACK button.
 * Each fragment that wants to support navigation backstack should
 * inherit from this class.
 */
abstract class BaseNavigationFragment : BaseFragment(),
    BackButtonHandler {

    @Inject
    lateinit var featureRouter: Router

    private lateinit var rootNavigator: RootNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootNavigator = activity!! as RootNavigator
        showRootNavigationView()

        val toolbar = view.findViewWithTag<Toolbar?>("jobajob.library.uicomponents.widget.Toolbar")
        toolbar?.setNavigationOnClickListener { rootNavigator.onSoftBackButtonPressed() }
    }

    fun hideRootNavigationView() {
        rootNavigator.hideNavigationView = true
    }

    fun showRootNavigationView() {
        rootNavigator.hideNavigationView = false
    }

    fun requestUserAuthorization() = rootNavigator.onNeedUserAuthorization()

    override fun onBackPressed(): Boolean = false
}