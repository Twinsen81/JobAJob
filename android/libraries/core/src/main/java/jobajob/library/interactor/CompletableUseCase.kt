package jobajob.library.interactor

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
    private val observeOn: Scheduler
) : UseCase() {

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open operator fun invoke(completableObserver: DisposableCompletableObserver, params: Params? = null) {
        val completable = this.buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(observeOn)
        addDisposable(completable.subscribeWith(completableObserver))
    }
}