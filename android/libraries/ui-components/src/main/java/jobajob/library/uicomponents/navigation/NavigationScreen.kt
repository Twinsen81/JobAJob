package jobajob.library.uicomponents.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NavigationScreen(val name: String, screenFragment: () -> Fragment): SupportAppScreen() {
    private val screenFragment: Fragment by lazy { screenFragment() }
    override fun getScreenKey() = name
    override fun getFragment() = screenFragment
}