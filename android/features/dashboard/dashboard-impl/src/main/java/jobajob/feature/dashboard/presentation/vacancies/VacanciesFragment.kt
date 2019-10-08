package jobajob.feature.dashboard.presentation.vacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.dashboard.R
import jobajob.feature.dashboard.data.remote.api.DashboardApi
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.library.uicomponents.presentation.BaseNavigationFragment
import javax.inject.Inject


internal class VacanciesFragment : BaseNavigationFragment() {

    private lateinit var viewModel: VacanciesViewModel

    @Inject
    lateinit var api: DashboardApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dashboard_fragment_vacancies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DashboardFeatureComponent.get().inject(this)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(VacanciesViewModel::class.java)

        /*btnVacancy.setOnClickListener {
            featureRouter.navigateTo(object : SupportAppScreen() {
                override fun getFragment(): Fragment = VacancyDetailFragment.newInstance(6)
            })
        }*/

        viewModel.loadVacancies()
    }
}