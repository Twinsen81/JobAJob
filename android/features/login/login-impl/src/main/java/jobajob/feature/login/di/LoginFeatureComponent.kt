package jobajob.feature.login.di

import dagger.Component
import jobajob.library.utils.di.PerFeature
import jobajob.library.utils.di.UtilsApi

@Component(modules = [LoginFeatureModule::class], dependencies = [LoginFeatureDependencies::class])
@PerFeature
abstract class LoginFeatureComponent : LoginFeatureApi {

    companion object {
        private var loginFeatureComponent: LoginFeatureComponent? = null

        fun initAndGet(dependencies: LoginFeatureDependencies): LoginFeatureApi {
            if (loginFeatureComponent == null) {
                synchronized(LoginFeatureComponent::class) {
                    if (loginFeatureComponent == null) {
                        loginFeatureComponent = DaggerLoginFeatureComponent.builder()
                            .loginFeatureDependencies(dependencies)
                            .build()
                    }
                }
            }
            return loginFeatureComponent!!
        }

        fun get(): LoginFeatureComponent {
            require(loginFeatureComponent != null)
                { "You must call LoginFeatureComponent.initAndGet prior this call!" }
            return loginFeatureComponent!!
        }

        fun resetComponent() {
            loginFeatureComponent = null
        }
    }

    @Component(dependencies = [UtilsApi::class])
    @PerFeature
    interface LoginFeatureDependenciesComponent : LoginFeatureDependencies
}