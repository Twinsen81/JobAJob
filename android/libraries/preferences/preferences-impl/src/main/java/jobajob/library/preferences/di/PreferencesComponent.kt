package jobajob.library.preferences.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import jobajob.library.preferences.PreferencesApi
import javax.inject.Singleton

@Component(modules = [PreferencesModule::class])
@Singleton
interface PreferencesComponent : PreferencesApi {

    @Component.Builder
    interface Builder {
        fun build(): PreferencesComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

    companion object {

        private var preferencesComponent: PreferencesComponent? = null

        fun initAndGet(appContext: Context): PreferencesApi {
            if (preferencesComponent == null) {
                synchronized(PreferencesComponent::class) {
                    if (preferencesComponent == null) {
                        preferencesComponent = DaggerPreferencesComponent.builder()
                            .application(appContext as Application)
                            .build()
                    }
                }
            }
            return preferencesComponent!!
        }

        fun get(): PreferencesApi {
            require(preferencesComponent != null) { "You must call initAndGet prior this call!" }
            return preferencesComponent!!
        }

        fun resetComponent() {
            preferencesComponent = null
        }
    }
}