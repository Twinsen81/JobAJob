package jobajob.feature.vacancies.api

import jobajob.feature.vacancies.di.FeatureInternal
import jobajob.feature.vacancies.network.VacanciesServerApi
import jobajob.feature.vacancies.usecase.GetEmployersUseCase
import jobajob.feature.vacancies.usecase.GetVacanciesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class VacanciesApiImpl @Inject constructor(
    private val serverApi: VacanciesServerApi,
    @FeatureInternal private val dispatcher: CoroutineDispatcher,
) : VacanciesFeatureApi {
    override fun getVacanciesUseCase(): GetVacanciesUseCase {
        TODO("Not yet implemented")
    }

    override fun getEmployersUseCase(): GetEmployersUseCase {
        TODO("Not yet implemented")
    }

}