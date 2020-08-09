package jobajob.library.navigation.api

import androidx.fragment.app.Fragment
import jobajob.library.navigation.api.NavigationEvent.HideNavigation

/**
 * A navigation event that [ScreenNavigator] sends to the activity. Notifies the activity
 * about what's happening with the navigation and also allows activity to receive commands
 * from fragments (e.g. [HideNavigation])
 */
sealed class NavigationEvent {

    /**
     * The active tab has been changed. User did it directly or by pressing the back button (when using the
     * common back stack type)
     */
    class TabChanged(val fragment: Fragment?, val tabIndex: Int) : NavigationEvent()

    /**
     * The top fragment of the current tab has been changed
     */
    class FragmentChanged(val fragment: Fragment?, val transactionType: FragmentTransactionType) : NavigationEvent()

    /**
     * A command from the current fragment to the activity - hide the navigation view, because the fragment wants to
     * display itself full screen
     */
    object HideNavigation : NavigationEvent()
}