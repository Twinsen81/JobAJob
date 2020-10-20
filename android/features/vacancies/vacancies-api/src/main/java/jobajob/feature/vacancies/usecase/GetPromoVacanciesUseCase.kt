package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.entity.DataPage
import jobajob.feature.vacancies.entity.PromoVacancy
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result

interface GetPromoVacanciesUseCase {
    /**
     * Get only promoted (featured) vacancies
     */
    suspend fun getPromoVacancies(): PromoVacanciesPageResult
}

typealias PromoVacanciesPageResult = Result<DataPage<PromoVacancy>, Failure>