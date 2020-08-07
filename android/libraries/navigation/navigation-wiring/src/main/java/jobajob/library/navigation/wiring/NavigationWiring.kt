package jobajob.library.navigation.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import jobajob.library.navigation.ScreenNavigatorFragNav
import jobajob.library.navigation.api.ScreenNavigator

@Module
@InstallIn(ActivityComponent::class)
object NavigationWiring {

    @Provides
    fun provideScreenNavigator(): ScreenNavigator = ScreenNavigatorFragNav()
}