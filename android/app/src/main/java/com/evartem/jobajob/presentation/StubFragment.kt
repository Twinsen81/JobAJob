package com.evartem.jobajob.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.login.api.LoginFeatureApi
import jobajob.library.session.AuthenticationData
import jobajob.library.session.Session
import jobajob.library.uicomponents.util.withArgs
import javax.inject.Inject

@AndroidEntryPoint
class StubFragment: Fragment() {

    @Inject
    lateinit var loginFeatureApi: LoginFeatureApi

    @Inject
    lateinit var session: Session

    private var stubTitle = "To be implemented..."

    companion object {
        private const val TITLE_ID_KEY = "title_id"

        fun newInstance(title: String) =
            StubFragment().withArgs {
                putString(TITLE_ID_KEY, title)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = requireNotNull(arguments)
        stubTitle = requireNotNull(args.getString(TITLE_ID_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context = container!!.context
        val constraintLayout = ConstraintLayout(context)
        constraintLayout.id = ViewCompat.generateViewId()

        val title = TextView(context)
        title.text = stubTitle
        title.textSize = 25f
        title.id = ViewCompat.generateViewId()
        title.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        title.setOnClickListener {
            if (session.authData is AuthenticationData.User) {
                loginFeatureApi.logout(requireActivity())
            } else {
                loginFeatureApi.login(requireActivity())
            }
        }

        constraintLayout.addView(title)

        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.connect(title.id, ConstraintSet.START, constraintLayout.id, ConstraintSet.START, 18)
        constraintSet.connect(title.id, ConstraintSet.END, constraintLayout.id, ConstraintSet.END, 18)
        constraintSet.connect(title.id, ConstraintSet.TOP, constraintLayout.id, ConstraintSet.TOP, 18)
        constraintSet.connect(title.id, ConstraintSet.BOTTOM, constraintLayout.id, ConstraintSet.BOTTOM, 18)
        constraintSet.applyTo(constraintLayout)

        return constraintLayout
    }
}