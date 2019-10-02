package com.evartem.jobajob.di

import android.app.Application
import com.evartem.jobajob.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
abstract class AppComponent {

    companion object {
        private lateinit var appComponent: AppComponent
        fun init(application: Application) {
            require(!::appComponent.isInitialized) { "AppComponent is already initialized. The init() must be called only once!" }
            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
        }

        fun get(): AppComponent {
            require(::appComponent.isInitialized) { "AppComponent is not initialized. Call the init() first!" }
            return appComponent
        }
    }

    abstract fun inject(activity: MainActivity)
}