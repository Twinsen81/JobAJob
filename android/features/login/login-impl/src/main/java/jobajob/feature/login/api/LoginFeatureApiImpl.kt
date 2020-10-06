package jobajob.feature.login.api

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import jobajob.feature.login.presentation.LoginActivity
import timber.log.Timber

object LoginFeatureApiImpl: LoginFeatureApi {
    override fun getLoginScreenIntent(context: Context): Intent {

        val authFragment = AuthFragment()
        val activity = context as FragmentActivity
        val fm = activity.supportFragmentManager
        fm.beginTransaction().add(authFragment, "auth#firebase").commit()

        //return LoginActivity.createIntent(context);
    }

    override fun logout(context: Context) {
        AuthUI.getInstance().signOut(context).addOnCompleteListener {
            Timber.v("New auth info: LOGGED OUT!")
        }
    }
}

class AuthFragment : Fragment() {


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
            1
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    Timber.v("New auth info: $user")
                    /*sessionManager.setAuthenticationData(
                        AuthenticationData.User(
                            uid = user.uid,
                            displayName = user.displayName,
                            photoUrl = user.photoUrl?.toString(),
                            email = user.email,
                            isAnonymous = user.isAnonymous
                        )
                    )*/
                } else {
                    //sessionManager.setAuthenticationData(AuthenticationData.error)
                }
            } else {
                //sessionManager.setAuthenticationData(AuthenticationData.error)
            }
        }

        val activity = context as FragmentActivity
        val fm = activity.supportFragmentManager
        fm.findFragmentByTag("auth#firebase")?.also {
            fm.beginTransaction().remove(it).commit()
        }
    }

}