package jobajob.library.navigation.api

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * A screen navigator for "Single activity"-style navigation between fragments with optional
 * tabs support
 */
interface ScreenNavigator {

    /**
     * Initialize the navigator with the activity's fragment manager and the root container.
     *
     * @param fragmentManager the [FragmentManager] of the main activity
     * @param containerId the container in the activity's layout where the fragments will be inflated
     * @param tabsNumber the number of root fragments - "tabs" that have independent back stack
     * @param initialTabIndex this tab will be shown on the app's start (zero-based index of the tab)
     * @param commonBackStack true - one back stack for all tabs (the back action can switch tabs),
     * false - each tab has it's own back stack (the back action on the root fragment of the tab will close the app)
     * @param savedInstanceState the activity's saved state to restore the navigation state
     * @param rootTabFragmentCreator a callback for dynamic root fragment (tab) creation
     * @param eventListener a listener to notify about navigation events (a tab/fragment change, etc.)
     */
    fun initialize(
        fragmentManager: FragmentManager,
        containerId: Int,
        tabsNumber: Int = 1,
        initialTabIndex: Int = 0,
        commonBackStack: Boolean = true,
        savedInstanceState: Bundle? = null,
        rootTabFragmentCreator: (tabIndex: Int) -> Fragment,
        eventListener: ((navigationEvent: NavigationEvent) -> Unit)? = null
    )

    /**
     * Switch the active tab to the [tabIndex]
     */
    fun switchTab(tabIndex: Int)

    /**
     * Navigate to a particular fragment (display that fragment).
     * The fragment will be added to back stack unless [addToBackStack] = false (then the
     * previous fragment will be replaced with the new one)
     */
    fun navigateTo(fragment: Fragment, addToBackStack: Boolean = true)

    /**
     * Go back the fragments' back stack (pop fragments).
     * Go back skipping several fragments by specifying [depth] > 0
     *
     * @return true if the back action was handled by the navigator (fragments were popped)
     */
    fun goBack(depth: Int = 0): Boolean

    /**
     * Clear the back stack and display the root (first) fragment
     */
    fun goToRoot()

    /**
     * Call this in the activity's onSaveInstanceState to save the navigation state
     */
    fun onSaveInstanceState(outState: Bundle)

    /**
     * Delete the reference to the event listener passed in the [initialize] function
     */
    fun removeEventListener()

    /**
     * Hide the navigation view until the next fragment transaction
     */
    fun hideNavigation()
}