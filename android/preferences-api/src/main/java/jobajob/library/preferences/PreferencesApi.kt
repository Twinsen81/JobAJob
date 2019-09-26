package jobajob.library.preferences

import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PreferencesApi {

    fun <T> asObservable(name: PreferenceName): Observable<T>
    fun <T> asConsumer(name: PreferenceName): Consumer<T>
}