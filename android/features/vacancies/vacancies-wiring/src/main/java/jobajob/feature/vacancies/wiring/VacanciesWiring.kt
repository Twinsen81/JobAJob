package jobajob.feature.vacancies.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jobajob.feature.vacancies.api.VacanciesBaseUrl

@Module
@InstallIn(SingletonComponent::class)
object VacanciesWiring {
    @Provides
    @VacanciesBaseUrl
    fun getBaseUrl() = "https://jobajob81.firebaseio.com"
}