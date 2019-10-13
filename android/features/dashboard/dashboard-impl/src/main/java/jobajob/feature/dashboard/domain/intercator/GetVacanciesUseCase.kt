package jobajob.feature.dashboard.domain.intercator

import io.reactivex.Scheduler
import io.reactivex.Single
import jobajob.feature.dashboard.domain.gateway.VacanciesGateway
import jobajob.feature.dashboard.domain.gateway.VacanciesListResult
import jobajob.library.intercator.SingleUseCase
import javax.inject.Inject

internal class GetVacanciesUseCase @Inject constructor(
    private val vacanciesRepository: VacanciesGateway,
    observeOn: Scheduler
): SingleUseCase<VacanciesListResult, GetVacanciesUseCase.Params>(observeOn) {
    override fun buildUseCaseSingle(params: Params?): Single<VacanciesListResult> =
        vacanciesRepository.getVacancies(params!!.page)

    data class Params(val page: Int)
}