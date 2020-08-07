package jobajob.library.navigation.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import jobajob.library.navigation.ScreenNavigatorFragNav
import jobajob.library.navigation.api.ScreenNavigator

@Module
@InstallIn(ActivityComponent::class)
object NavigationWiring {

    @Provides
    @ActivityScoped
    fun provideScreenNavigator(): ScreenNavigator = ScreenNavigatorFragNav()
}