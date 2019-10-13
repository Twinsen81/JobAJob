package jobajob.feature.dashboard.presentation.vacancies

import androidx.paging.PageKeyedDataSource
import io.reactivex.observers.DisposableSingleObserver
import jobajob.feature.dashboard.domain.intercator.GetVacanciesUseCase
import jobajob.library.entity.vacancy.Vacancy

internal class VacanciesPagedDataSource(private val getVacanciesUseCase: GetVacanciesUseCase) :PageKeyedDataSource<Int, Vacancy>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Vacancy>
    ) {
        getVacanciesUseCase(
            singleObserver { result ->
                result.either(
                    { list -> callback.onResult(list.toMutableList(), null, 1) },
                    { })
            }, GetVacanciesUseCase.Params(0))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Vacancy>) {
        getVacanciesUseCase(
            singleObserver { result ->
                result.either(
                    { list -> callback.onResult(list.toMutableList(), params.key + 1) },
                    { })
            }, GetVacanciesUseCase.Params(params.key))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Vacancy>) = Unit

    protected fun <T> singleObserver(onSuccess: (T) -> Unit) =
        object : DisposableSingleObserver<T>() {

            override fun onSuccess(t: T) {
                onSuccess.invoke(t)
            }

            override fun onError(e: Throwable) {
            }
        }
}