package jobajob.library.uicomponents.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jobajob.library.utils.KeyStoreUtils
import jobajob.library.utils.Utils
import jobajob.library.utils.di.UtilsApi

@Module
@InstallIn(ApplicationComponent::class)
object LibrariesWiring {

    @Provides
    fun provideUtils(): UtilsApi = object : UtilsApi {
        override fun utils(): Utils = Utils()
        override fun keyStoreUtils(): KeyStoreUtils = KeyStoreUtils()
    }
}