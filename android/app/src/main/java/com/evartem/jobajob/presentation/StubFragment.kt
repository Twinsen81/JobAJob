package com.evartem.jobajob.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jobajob.library.uicomponents.util.withArgs
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.constraintlayout.widget.ConstraintSet

class StubFragment: Fragment() {

    private var stubTitle = "To be implemented..."

    companion object {
        private const val TITLE_ID_KEY = "title_id"

        fun newInstance(title: String)  =
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