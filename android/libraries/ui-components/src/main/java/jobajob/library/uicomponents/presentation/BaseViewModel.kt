package jobajob.library.uicomponents.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import jobajob.library.entity.common.Failure
import jobajob.library.interactor.UseCase

/**
 * Base class for view models that provides automatic observer disposing,
 * failure handling and observer creation convenience functions.
 */
open class BaseViewModel(vararg useCases: UseCase) : ViewModel() {

    private val disposableUseCases = useCases

    private val _failure = SingleLiveEvent<Failure>()
    val failure: LiveData<Failure>
        get() = _failure

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

    protected fun <T> observer(onNext: (T) -> Unit, onComplete: (() -> Unit)? = null) =
        object : DisposableObserver<T>() {

            override fun onComplete() {
                onComplete?.invoke()
            }

            override fun onNext(t: T) {
                onNext.invoke(t)
            }

            override fun onError(e: Throwable) {
                handleFailure(Failure.ApplicationError(e))
            }
        }

    protected fun <T> singleObserver(onSuccess: (T) -> Unit) =
        object : DisposableSingleObserver<T>() {

            override fun onSuccess(t: T) {
                onSuccess.invoke(t)
            }

            override fun onError(e: Throwable) {
                handleFailure(Failure.ApplicationError(e))
            }
        }

    override fun onCleared() {
        super.onCleared()
        disposableUseCases.forEach { it.dispose() }
    }
}