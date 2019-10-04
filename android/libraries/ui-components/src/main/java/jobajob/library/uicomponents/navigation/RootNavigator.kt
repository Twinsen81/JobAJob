package jobajob.library.uicomponents.navigation

/**
 * Implemented by the main activity (root navigation host of all fragments) of the app.
 * Lets features communicate with it.
 */
interface RootNavigator {
    /**
     * Ask the root navigator to hide/show the main navigation view.
     * Set to true if your feature's fragment needs fullscreen.
     */
    var hideNavigationView: Boolean

    /**
     * A feature's fragment calls this when an UI back button (e.g. on the toolbar) was clicked.
     */
    fun onSoftBackButtonPressed()
}
