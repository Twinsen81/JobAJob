package jobajob.library.navigation

import android.content.Intent
import androidx.fragment.app.Fragment

sealed class Target {
    class FragmentTarget(val fragment: Fragment): Target()
    class IntentTarget(val intent: Intent): Target()
}