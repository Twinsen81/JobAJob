package jobajob.feature.favorites.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jobajob.feature.favorites.presentation.favorites.FavoritesViewModel
import jobajob.library.uicomponents.di.FeatureViewModelFactoryModule
import jobajob.library.uicomponents.di.ViewModelKey

@Module(includes = [FeatureViewModelFactoryModule::class])
internal abstract class ViewModelModule {

    @[Binds IntoMap ViewModelKey(FavoritesViewModel::class)]
    abstract fun bindViewModel(viewModel: FavoritesViewModel): ViewModel
}
