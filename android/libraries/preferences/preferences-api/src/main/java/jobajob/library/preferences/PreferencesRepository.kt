package jobajob.library.preferences

import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PreferencesRepository {
    fun getString(preferenceName: StringPreference, defaultValue: String = ""): String
    fun observeString(preferenceName: StringPreference, defaultValue: String = ""): Observable<String>
    fun assignString(preferenceName: StringPreference): Consumer<in String>
    fun setString(preferenceName: StringPreference, value: String)

    fun getBoolean(preferenceName: BooleanPreference, defaultValue: Boolean): Boolean
    fun observeBoolean(preferenceName: BooleanPreference, defaultValue: Boolean): Observable<Boolean>
    fun assignBoolean(preferenceName: BooleanPreference): Consumer<in Boolean>
    fun setBoolean(preferenceName: BooleanPreference, value: Boolean)

    fun getInt(preferenceName: IntPreference, defaultValue: Int): Int
    fun observeInt(preferenceName: IntPreference, defaultValue: Int): Observable<Int>
    fun assignInt(preferenceName: IntPreference): Consumer<in Int>
    fun setInt(preferenceName: IntPreference, value: Int)
}