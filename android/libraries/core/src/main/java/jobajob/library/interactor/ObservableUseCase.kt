package jobajob.library.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(
    private val observeOn: Scheduler
) : UseCase() {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open operator fun invoke(singleObserver: DisposableObserver<T>, params: Params? = null) {
        val single = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(observeOn)
        addDisposable(single.subscribeWith(singleObserver))
    }
}