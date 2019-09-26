package jobajob.feature.login.di

import dagger.Module
import dagger.Provides
import jobajob.feature.login.domain.SessionInfo
import jobajob.library.utils.Utils

@Module
object LoginFeatureModule {

    @JvmStatic
    @Provides
    fun provideSessionInfo(utils: Utils) = SessionInfo("me@me.com", utils.generateRandomUserId())
}