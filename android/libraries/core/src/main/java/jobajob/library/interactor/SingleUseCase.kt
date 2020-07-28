package jobajob.library.interactor

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, in Params> constructor(
    private val observeOn: Scheduler
) : UseCase() {

    protected abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open operator fun invoke(singleObserver: DisposableSingleObserver<T>, params: Params? = null) {
        val single = this.buildUseCaseSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(observeOn)
        addDisposable(single.subscribeWith(singleObserver))
    }
}