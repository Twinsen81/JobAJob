package jobajob.library.uicomponents.presentation

import androidx.fragment.app.Fragment
import jobajob.library.uicomponents.navigation.BackButtonHandler

abstract class BaseFeatureFragment : Fragment(), BackButtonHandler {
    override fun onBackPressed(): Boolean = false
}