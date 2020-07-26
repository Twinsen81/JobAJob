package jobajob.library.preferences.api

import android.content.Context
import jobajob.library.preferences.PreferencesApi
import jobajob.library.preferences.PreferencesRepository
import jobajob.library.preferences.SharedPreferencesRepository

class PreferencesApiImpl(private val context: Context) : PreferencesApi {

    private val repositories: MutableMap<String, PreferencesRepository> = mutableMapOf()

    override fun getPreferencesRepository(fileName: String): PreferencesRepository {
        val prefsFileName = fileName.ifEmpty { "JobAJob" }
        return repositories[prefsFileName] ?: synchronized(this) {
            repositories[prefsFileName] ?: repositories.run {
                this[prefsFileName] = SharedPreferencesRepository(
                    context = context,
                    fileName = prefsFileName
                )
                this[prefsFileName]!!
            }
        }
    }
}