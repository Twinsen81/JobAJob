package jobajob.feature.login.api

import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Logging user in and out with UI
 */
@ExperimentalCoroutinesApi
interface LoginFeatureApi {

    /**
     * Login a user
     *
     * @param activity The activity to be used as the context for the authorization UI
     */
    fun login(activity: FragmentActivity)

    /**
     * Logout the logged-in user
     *
     * @param activity The activity to be used as the context for the authorization UI
     */
    fun logout(activity: FragmentActivity)
}