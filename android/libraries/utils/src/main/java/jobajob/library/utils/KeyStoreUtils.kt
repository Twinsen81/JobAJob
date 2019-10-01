package jobajob.library.utils

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeyStoreUtils @Inject constructor() {
    fun encrypt(keyAlias: String, data: String): String {
        TODO()
    }

    fun decrypt(keyAlias: String, encryptedData: String): String {
        TODO()
    }
}