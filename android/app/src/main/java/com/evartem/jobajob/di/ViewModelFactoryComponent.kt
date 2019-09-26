package com.evartem.jobajob.di

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evartem.jobajob.LoginActivity
import com.evartem.jobajob.LoginViewModel
import com.evartem.jobajob.MainActivity
import com.evartem.jobajob.MainActivityViewModel
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(private val providers: Map<
            Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>)
        : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider = providers[modelClass] ?:
        providers.asIterable().firstOrNull {modelClass.isAssignableFrom(it.key)}
            ?.value
        ?: throw IllegalArgumentException("Unknown model class: $modelClass")

        try {

            val model = provider.get() as T
            Log.d("ViewModelFactory", "Factory: ${this}, key: $modelClass, provider: $provider, model: $model")
            return model
        }catch(ex: Exception) {
            throw RuntimeException(ex)
        }
    }
}

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Component(modules = [ActivityModule::class])
interface ViewModelFactoryComponent {
    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)
}

@Module
interface ActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindViewModelMain(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindViewModelLogin(viewModel: LoginViewModel): ViewModel

}