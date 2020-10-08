package jobajob.feature.login.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import jobajob.feature.login.api.LoginFeatureApi
import jobajob.feature.login.api.LoginFeatureApiImpl

@Module
@InstallIn(ActivityComponent::class)
object LoginWiring {

    @Provides
    @ActivityScoped
    fun provideLoginFeature(apiImpl: LoginFeatureApiImpl): LoginFeatureApi = apiImpl
}