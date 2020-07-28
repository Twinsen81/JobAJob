package jobajob.library.uicomponents.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jobajob.library.uicomponents.navigation.BackButtonHandler
import jobajob.library.uicomponents.navigation.RootNavigator
import jobajob.library.uicomponents.widget.Toolbar
import ru.terrakok.cicerone.Router

/**
 * A fragment of a feature that can navigate to other fragments and
 * can handle the hardware BACK button.
 * Each fragment that wants to support navigation backstack should
 * inherit from this class.
 */
abstract class BaseNavigationFragment : Fragment(), BackButtonHandler {

    private lateinit var rootNavigator: RootNavigator
    protected val router: Router
        get() {
            var parent: Fragment? = parentFragment
            while (parent != null) {
                if (parent is FeatureNavigationHostFragment) return parent.featureRouter
                parent = parent.parentFragment
            }
            throw IllegalStateException(
                "Fragment ${this.javaClass.simpleName} must have FeatureNavigationHostFragment as " +
                        "the parent"
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootNavigator = requireActivity() as RootNavigator
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