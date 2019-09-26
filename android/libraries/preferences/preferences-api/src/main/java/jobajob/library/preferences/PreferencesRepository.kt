package jobajob.library.preferences

import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PreferencesRepository {
    fun observeString(preferenceName: PreferenceName, defaultValue: String = ""): Observable<String>
    fun assignString(preferenceName: PreferenceName): Consumer<in String>

    fun observeBoolean(preferenceName: PreferenceName, defaultValue: Boolean = false): Observable<Boolean>
    fun assignBoolean(preferenceName: PreferenceName): Consumer<in Boolean>

    fun observeInt(preferenceName: PreferenceName, defaultValue: Int = 0): Observable<Int>
    fun assignInt(preferenceName: PreferenceName): Consumer<in Int>
}