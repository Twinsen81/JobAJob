package jobajob.library.uicomponents.navigation

/**
 * A fragment should implement this interface if it wants to handle the hardware back button
 * pressed event. The hosting activity passes delegates processing of the event to all its
 * fragments who implement this interface.
 * @return true if the fragment has handled the back pressed event and there's no need to do
 * anything else (e.g. call super.onBackPressed)
 * @return false if the activity or another fragment should handle the event
 */
interface BackButtonHandler {
    fun onBackPressed(): Boolean
}