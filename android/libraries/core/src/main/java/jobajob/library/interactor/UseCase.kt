package jobajob.library.interactor

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase {

    private var lastDisposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(d: Disposable) {
        compositeDisposable.add(d)
        lastDisposable = d
    }

    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}