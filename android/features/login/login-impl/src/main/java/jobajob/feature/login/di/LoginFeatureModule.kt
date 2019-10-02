package jobajob.feature.login.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jobajob.feature.login.LoginViewModel
import jobajob.library.uicomponents.di.FeatureViewModelFactoryModule
import jobajob.library.uicomponents.di.ViewModelKey

@Module(includes = [FeatureViewModelFactoryModule::class])
internal abstract class LoginFeatureModule {

    @[Binds IntoMap ViewModelKey(LoginViewModel::class)]
    abstract fun bindViewModel(viewModel: LoginViewModel): ViewModel
}
