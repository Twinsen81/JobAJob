package jobajob.library.uicomponents.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class FeatureNavigator @Inject constructor(
    private val ciceroneRouter: Router,
    private val ciceroneNavigatorHolder: NavigatorHolder
) {
    private var ciceroneNavigator: Navigator? = null

    fun setNavigationHost(
        activity: FragmentActivity,
        fragmentManager: FragmentManager, @IdRes containerId: Int
    ) {
        if (ciceroneNavigator == null)
            ciceroneNavigator = SupportAppNavigator(activity, fragmentManager, containerId)
    }

    fun startNavigator() = ciceroneNavigatorHolder.setNavigator(ciceroneNavigator!!)

    fun pauseNavigator() = ciceroneNavigatorHolder.removeNavigator()


}