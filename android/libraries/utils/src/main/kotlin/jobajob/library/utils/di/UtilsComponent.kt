package jobajob.library.utils.di

import dagger.Component
import jobajob.library.utils.Utils
import javax.inject.Singleton

@Component
@Singleton
interface UtilsComponent : UtilsApi {
    companion object {
        val instance: UtilsComponent = DaggerUtilsComponent.builder().build()
    }

    override fun utils(): Utils = Utils()
}