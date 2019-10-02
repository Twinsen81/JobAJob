package jobajob.feature.favorites.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import jobajob.feature.favorites.FavoritesFragment
import jobajob.feature.favorites.FavoritesViewModel
import jobajob.library.uicomponents.di.FeatureViewModelFactoryModule
import jobajob.library.uicomponents.di.ViewModelKey
import jobajob.library.utils.di.PerFeature

@Module(includes = [FeatureViewModelFactoryModule::class])
internal abstract class FavoritesFeatureModule {

    @[Binds IntoMap ViewModelKey(FavoritesViewModel::class)]
    abstract fun bindViewModel(viewModel: FavoritesViewModel): ViewModel

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideFeatureFragment(): Fragment = FavoritesFragment()
    }
}
