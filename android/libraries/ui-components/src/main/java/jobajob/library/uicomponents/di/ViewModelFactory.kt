package jobajob.library.uicomponents.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * The factory class that overrides [ViewModelProvider.Factory] create method
 * to look for the requested [ViewModel] class in the Dagger's generated map of
 * class -> ViewModel.
 *
 * Use the following syntax in a Dagger's module to insert a ViewModel class into the map:
 *
 * @[Binds IntoMap ViewModelKey(MyViewModel::class)]
 * abstract fun bindMyViewModel(myViewModel: MyViewModel): ViewModel
 */
class ViewModelFactory(
    private val providers: Map<
            Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider = providers[modelClass]
            ?: providers.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }
                ?.value
            ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")

        @Suppress("UNCHECKED_CAST")
        try {
            return provider.get() as T
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }
}