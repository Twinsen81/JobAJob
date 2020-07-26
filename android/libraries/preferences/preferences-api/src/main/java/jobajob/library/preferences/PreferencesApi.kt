package jobajob.library.preferences

interface PreferencesApi {
    fun getPreferencesRepository(fileName: String): PreferencesRepository
}