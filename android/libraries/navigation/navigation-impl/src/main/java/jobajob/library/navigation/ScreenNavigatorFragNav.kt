package jobajob.library.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UnlimitedTabHistoryStrategy
import jobajob.library.navigation.api.FragmentTransactionType
import jobajob.library.navigation.api.NavigationEvent
import jobajob.library.navigation.api.ScreenNavigator

/**
 * The [ScreenNavigator] implementation using the FragNav library
 */
class ScreenNavigatorFragNav : ScreenNavigator {

    private lateinit var fragNavController: FragNavController

    private var eventListener: ((navigationEvent: NavigationEvent) -> Unit)? = null

    private var commonBackStack = true

    override fun initialize(
        fragmentManager: FragmentManager,
        containerId: Int,
        tabsNumber: Int,
        initialTabIndex: Int,
        commonBackStack: Boolean,
        savedInstanceState: Bundle?,
        rootTabFragmentCreator: (tabIndex: Int) -> Fragment,
        eventListener: ((navigationEvent: NavigationEvent) -> Unit)?
    ) {
        fragNavController = FragNavController(fragmentManager, containerId)
        this.eventListener = eventListener
        this.commonBackStack = commonBackStack

        fragNavController.apply {

            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH

            rootFragmentListener = object : FragNavController.RootFragmentListener {
                override val numberOfRootFragments: Int = tabsNumber
                override fun getRootFragment(index: Int) = rootTabFragmentCreator.invoke(index)
            }

            transactionListener = object : FragNavController.TransactionListener {
                override fun onFragmentTransaction(
                    fragment: Fragment?,
                    transactionType: FragNavController.TransactionType
                ) {
                    eventListener?.invoke(NavigationEvent.FragmentChanged(fragment, transactionType.toApiType()))
                }

                override fun onTabTransaction(fragment: Fragment?, index: Int) {
                    eventListener?.invoke(NavigationEvent.TabChanged(fragment, index))
                }
            }

            navigationStrategy = UnlimitedTabHistoryStrategy(object : FragNavSwitchController {
                override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
                    fragNavController.switchTab(index)
                }
            })

            initialize(initialTabIndex, savedInstanceState)
        }
    }

    override fun switchTab(tabIndex: Int) {
        fragNavController.switchTab(tabIndex)
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        if (addToBackStack) {
            fragNavController.pushFragment(fragment)
        } else {
            fragNavController.replaceFragment(fragment)
        }
    }

    override fun goBack(depth: Int): Boolean {
        return if (depth > 0) {
            fragNavController.popFragments(depth)
            true
        } else {
            if (commonBackStack) {
                fragNavController.popFragment()
            } else {
                goBackIfNotRootFragment()
            }
        }
    }

    private fun goBackIfNotRootFragment(): Boolean {
        return if (fragNavController.isRootFragment) {
            false
        } else {
            fragNavController.popFragment()
        }
    }

    override fun goToRoot() {
        fragNavController.clearStack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        fragNavController.onSaveInstanceState(outState)
    }

    override fun removeEventListener() {
        eventListener = null
    }

    override fun hideNavigation() {
        eventListener?.invoke(NavigationEvent.HideNavigation)
    }

    private fun FragNavController.TransactionType.toApiType(): FragmentTransactionType =
        when (this) {
            FragNavController.TransactionType.PUSH -> FragmentTransactionType.ADD
            FragNavController.TransactionType.POP -> FragmentTransactionType.GO_BACK
            FragNavController.TransactionType.REPLACE -> FragmentTransactionType.REPLACE
        }
}