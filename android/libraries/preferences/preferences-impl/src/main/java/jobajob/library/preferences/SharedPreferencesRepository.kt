package jobajob.library.preferences

import android.content.Context
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject

internal class SharedPreferencesRepository @Inject constructor(
    context: Context,
    fileName: String
) : PreferencesRepository {

    private val rxSharedPreferences =
        RxSharedPreferences.create(context.getSharedPreferences(fileName, Context.MODE_PRIVATE))

    override fun getString(preferenceName: StringPreference, defaultValue: String): String =
        rxSharedPreferences.getString(preferenceName.name, defaultValue).get()

    override fun observeString(preferenceName: StringPreference, defaultValue: String): Observable<String> =
        rxSharedPreferences.getString(preferenceName.name, defaultValue).asObservable()

    override fun assignString(preferenceName: StringPreference): Consumer<in String> =
        rxSharedPreferences.getString(preferenceName.name).asConsumer()

    override fun setString(preferenceName: StringPreference, value: String) =
        rxSharedPreferences.getString(preferenceName.name).set(value)

    override fun getBoolean(preferenceName: BooleanPreference, defaultValue: Boolean): Boolean =
        rxSharedPreferences.getBoolean(preferenceName.name, defaultValue).get()

    override fun observeBoolean(preferenceName: BooleanPreference, defaultValue: Boolean): Observable<Boolean> =
        rxSharedPreferences.getBoolean(preferenceName.name, defaultValue).asObservable()

    override fun assignBoolean(preferenceName: BooleanPreference): Consumer<in Boolean> =
        rxSharedPreferences.getBoolean(preferenceName.name).asConsumer()

    override fun setBoolean(preferenceName: BooleanPreference, value: Boolean) =
        rxSharedPreferences.getBoolean(preferenceName.name).set(value)

    override fun getInt(preferenceName: IntPreference, defaultValue: Int): Int =
        rxSharedPreferences.getInteger(preferenceName.name, defaultValue).get()

    override fun observeInt(preferenceName: IntPreference, defaultValue: Int): Observable<Int> =
        rxSharedPreferences.getInteger(preferenceName.name, defaultValue).asObservable()

    override fun assignInt(preferenceName: IntPreference): Consumer<in Int> =
        rxSharedPreferences.getInteger(preferenceName.name).asConsumer()

    override fun setInt(preferenceName: IntPreference, value: Int) =
        rxSharedPreferences.getInteger(preferenceName.name).set(value)
}