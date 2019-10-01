package jobajob.library.utils.di

import dagger.Component
import jobajob.library.utils.KeyStoreUtils
import jobajob.library.utils.Utils
import javax.inject.Singleton

@Component
@Singleton
interface UtilsComponent : UtilsApi {
    companion object {
        val instance: UtilsComponent = DaggerUtilsComponent.create()
    }

    override fun utils(): Utils = Utils()
    override fun keyStoreUtils(): KeyStoreUtils = KeyStoreUtils()
}