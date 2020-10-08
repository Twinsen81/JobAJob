package jobajob.feature.login.api

import androidx.fragment.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import jobajob.feature.login.presentation.AuthenticationFragment
import jobajob.feature.login.presentation.AuthenticationFragment.Companion.FRAGMENT_TAG
import jobajob.library.session.AuthenticationData
import jobajob.library.session.SessionManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Firebase Authentication based implementation of the [LoginFeatureApi]
 */
@ExperimentalCoroutinesApi
class LoginFeatureApiImpl @Inject constructor(private val sessionManager: SessionManager) : LoginFeatureApi {

    override fun login(activity: FragmentActivity) {
        val authFragment = AuthenticationFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().add(authFragment, FRAGMENT_TAG).commit()
    }

    override fun logout(activity: FragmentActivity) {
        AuthUI.getInstance().signOut(activity).addOnCompleteListener {
            sessionManager.setAuthenticationData(AuthenticationData.none)
        }
    }
}
