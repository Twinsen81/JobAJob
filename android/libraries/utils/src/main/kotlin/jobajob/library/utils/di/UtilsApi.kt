package jobajob.library.utils.di

import jobajob.library.utils.KeyStoreUtils
import jobajob.library.utils.Utils

interface UtilsApi {
    fun utils(): Utils
    fun keyStoreUtils(): KeyStoreUtils
}