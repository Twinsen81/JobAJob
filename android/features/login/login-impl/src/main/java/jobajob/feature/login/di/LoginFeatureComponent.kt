package jobajob.feature.login.di

import android.content.Context
import android.content.Intent
import dagger.Component
import jobajob.feature.login.presentation.LoginActivity
import jobajob.library.utils.di.PerFeature
import jobajob.library.utils.di.UtilsApi

@Component(
    modules = [ViewModelModule::class],
    dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class LoginFeatureComponent : LoginFeatureApi {

    companion object {
        private var featureComponent: LoginFeatureComponent? = null

        fun initAndGet(dependencies: FeatureDependencies): LoginFeatureApi {
            if (featureComponent == null) {
                synchronized(LoginFeatureComponent::class) {
                    if (featureComponent == null) {
                        featureComponent = DaggerLoginFeatureComponent.builder()
                            .featureDependencies(dependencies)
                            .build()
                    }
                }
            }
            return featureComponent!!
        }

        fun get(): LoginFeatureComponent {
            require(featureComponent != null) { "You must call initAndGet prior this call!" }
            return featureComponent!!
        }

        fun resetComponent() {
            featureComponent = null
        }
    }

    @Component(dependencies = [UtilsApi::class])
    @PerFeature
    interface FeatureDependenciesComponent : FeatureDependencies

    override fun getLoginScreenIntent(context: Context): Intent =
        Intent(context, LoginActivity::class.java)

    internal abstract fun inject(activity: LoginActivity)
}