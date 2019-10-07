package jobajob.feature.dashboard.presentation.vacancies

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.dashboard.R
import jobajob.feature.dashboard.data.DashboardApi
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.feature.dashboard.presentation.vacancydetail.VacancyDetailFragment
import jobajob.library.uicomponents.navigation.BaseFeatureFragment
import kotlinx.android.synthetic.main.dashboard_fragment_vacancies.*
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject


internal class VacanciesFragment : BaseFeatureFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: VacanciesViewModel

    @Inject
    lateinit var featureRouter: Router
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

        btnVacancy.setOnClickListener {
            featureRouter.navigateTo(object : SupportAppScreen() {
                override fun getFragment(): Fragment = VacancyDetailFragment.newInstance(6)
            })
        }

        val runnable = Runnable {
            val response = api.fetchVacancies().execute()
            val data = response.body()!!.body()
        }
        AsyncTask.execute(runnable)
    }
}