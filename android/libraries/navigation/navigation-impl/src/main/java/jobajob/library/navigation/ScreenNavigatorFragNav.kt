package jobajob.library.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import jobajob.library.navigation.api.FragmentTransactionType
import jobajob.library.navigation.api.ScreenNavigator

/**
 * The [ScreenNavigator] implementation using the FragNav library
 */
class ScreenNavigatorFragNav : ScreenNavigator {

    private lateinit var fragNavController: FragNavController

    override fun initialize(
        fragmentManager: FragmentManager,
        containerId: Int,
        tabsNumber: Int,
        initialTabIndex: Int,
        savedInstanceState: Bundle?,
        rootTabFragmentCreator: (tabIndex: Int) -> Fragment,
        onTabChanged: ((fragment: Fragment?, tabIndex: Int) -> Unit)?,
        onFragmentChanged: ((fragment: Fragment?, transactionType: FragmentTransactionType) -> Unit)?
    ) {
        fragNavController = FragNavController(fragmentManager, containerId)

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
                ) = onFragmentChanged?.invoke(fragment, transactionType.toApiType()) ?: Unit

                override fun onTabTransaction(fragment: Fragment?, index: Int) =
                    onTabChanged?.invoke(fragment, index) ?: Unit
            }

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
            fragNavController.popFragment()
        }
    }

    override fun goToRoot() {
        fragNavController.clearStack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        fragNavController.onSaveInstanceState(outState)
    }

    private fun FragNavController.TransactionType.toApiType(): FragmentTransactionType =
        when (this) {
            FragNavController.TransactionType.PUSH -> FragmentTransactionType.ADD
            FragNavController.TransactionType.POP -> FragmentTransactionType.GO_BACK
            FragNavController.TransactionType.REPLACE -> FragmentTransactionType.REPLACE
        }
}