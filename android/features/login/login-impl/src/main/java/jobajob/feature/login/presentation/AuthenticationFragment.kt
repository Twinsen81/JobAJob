package jobajob.feature.login.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import jobajob.library.session.AuthenticationData
import jobajob.library.session.SessionManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * A fragment without UI whose sole purpose is to run the Firebase Authentication activity
 * and get results back from it
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
internal class AuthenticationFragment : Fragment() {

    @Inject
    lateinit var sessionManager: SessionManager

    companion object {
        private const val REQUEST_CODE_FIREBASE_AUTH = 1
        const val FRAGMENT_TAG = "firebaseauthui"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val authProviders = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(authProviders)
                .build(),
            REQUEST_CODE_FIREBASE_AUTH
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_FIREBASE_AUTH) {
            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    user.getIdToken(false).addOnCompleteListener { task ->
                        sessionManager.setAuthenticationData(
                            AuthenticationData.User(
                                uid = user.uid,
                                authToken = task.result?.token,
                                displayName = user.displayName,
                                photoUrl = user.photoUrl?.toString(),
                                email = user.email,
                                isAnonymous = user.isAnonymous
                            )
                        )
                        removeFragmentFromActivity()
                    }
                } else {
                    sessionManager.setAuthenticationData(AuthenticationData.error)
                    removeFragmentFromActivity()
                }
            } else {
                sessionManager.setAuthenticationData(AuthenticationData.error)
                removeFragmentFromActivity()
            }
        }
    }

    private fun removeFragmentFromActivity() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.findFragmentByTag(FRAGMENT_TAG)?.also {
            fragmentManager.beginTransaction().remove(it).commit()
        }
    }
}