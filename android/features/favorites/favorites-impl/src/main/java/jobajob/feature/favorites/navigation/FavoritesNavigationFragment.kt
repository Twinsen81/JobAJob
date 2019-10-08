package jobajob.feature.favorites.navigation

import androidx.fragment.app.Fragment
import jobajob.feature.favorites.di.FavoritesFeatureComponent
import jobajob.feature.favorites.presentation.favorites.FavoritesFragment
import jobajob.library.uicomponents.presentation.FeatureNavigationHostFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

internal class FavoritesNavigationFragment : FeatureNavigationHostFragment() {

    override fun injectDependencies() =
        FavoritesFeatureComponent.get().inject(this)


    override fun getStartScreen(): SupportAppScreen =
        object : SupportAppScreen() {
            override fun getFragment(): Fragment =
                FavoritesFragment()
        }
}