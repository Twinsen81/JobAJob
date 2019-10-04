package jobajob.library.uicomponents.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jobajob.library.uicomponents.widget.Toolbar

abstract class BaseFeatureFragment : Fragment(), BackButtonHandler {

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