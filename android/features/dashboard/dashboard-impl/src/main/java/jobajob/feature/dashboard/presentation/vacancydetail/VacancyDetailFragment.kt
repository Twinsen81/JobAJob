package jobajob.feature.dashboard.presentation.vacancydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.dashboard.R
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.library.uicomponents.navigation.BaseFeatureFragment
import jobajob.library.uicomponents.util.withArgs
import kotlinx.android.synthetic.main.fragment_vacancy_detail.*
import javax.inject.Inject

internal class VacancyDetailFragment: BaseFeatureFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: VacancyDetailViewModel

    private var vacancyId: Long = 0

    companion object {
        private const val VACANCY_ID_KEY = "vacancy_id"

        fun newInstance(vacancyId: Long)  =
            VacancyDetailFragment().withArgs {
                putLong(VACANCY_ID_KEY, vacancyId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = requireNotNull(arguments)
        vacancyId = requireNotNull(args.getLong(VACANCY_ID_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_vacancy_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DashboardFeatureComponent.get().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(VacancyDetailViewModel::class.java)

        dbVacTitle.text = "${dbVacTitle.text}  $vacancyId"

        hideRootNavigationView()

        btnAuthorize.setOnClickListener {
            requestUserAuthorization()
        }
    }
}