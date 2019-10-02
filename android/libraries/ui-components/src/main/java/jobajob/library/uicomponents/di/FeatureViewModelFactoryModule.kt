package jobajob.library.uicomponents.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import jobajob.library.utils.di.PerFeature
import javax.inject.Provider

/**
 * A Dagger's module that provides [ViewModelFactory] with [PerFeature] scope to reduce
 * boilerplate code.
 *
 * Use this in a feature's dagger module that inserts a [ViewModel] class into the map:
 *
 * @Module(includes = [FeatureViewModelFactoryModule::class])
 * abstract class MyFeatureModule {
 *      @[Binds IntoMap ViewModelKey(MyViewModel::class)]
 *      abstract fun bindMyViewModel(myViewModel: MyViewModel): ViewModel
 * }
 */
@Module
object FeatureViewModelFactoryModule {

    @[Provides JvmStatic PerFeature]
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory =
        ViewModelFactory(providerMap)
}