package jobajob.library.preferences

import android.app.Application
import androidx.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SharedPreferencesRepository @Inject constructor(application: Application) :
    PreferencesRepository {

    private val rxSharedPreferences =
        RxSharedPreferences.create(PreferenceManager.getDefaultSharedPreferences(application))

    override fun observeString(
        preferenceName: PreferenceName,
        defaultValue: String
    ): Observable<String> =
        rxSharedPreferences.getString(preferenceName.name, defaultValue).asObservable()


    override fun assignString(preferenceName: PreferenceName): Consumer<in String> =
        rxSharedPreferences.getString(preferenceName.name).asConsumer()

    override fun observeBoolean(
        preferenceName: PreferenceName,
        defaultValue: Boolean
    ): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun assignBoolean(preferenceName: PreferenceName): Consumer<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun observeInt(preferenceName: PreferenceName, defaultValue: Int): Observable<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun assignInt(preferenceName: PreferenceName): Consumer<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}