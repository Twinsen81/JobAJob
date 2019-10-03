package jobajob.feature.dashboard.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jobajob.feature.dashboard.presentation.main.DashboardViewModel
import jobajob.feature.dashboard.presentation.vacancies.VacanciesViewModel
import jobajob.feature.dashboard.presentation.vacancydetail.VacancyDetailViewModel
import jobajob.library.uicomponents.di.FeatureViewModelFactoryModule
import jobajob.library.uicomponents.di.ViewModelKey

@Module(includes = [FeatureViewModelFactoryModule::class])
internal abstract class DashboardFeatureModule {

    @[Binds IntoMap ViewModelKey(DashboardViewModel::class)]
    abstract fun bindViewModelDashboard(viewModel: DashboardViewModel): ViewModel

    @[Binds IntoMap ViewModelKey(VacanciesViewModel::class)]
    abstract fun bindViewModelVacancies(viewModel: VacanciesViewModel): ViewModel

    @[Binds IntoMap ViewModelKey(VacancyDetailViewModel::class)]
    abstract fun bindViewModelVacancyDetail(viewModel: VacancyDetailViewModel): ViewModel
}
