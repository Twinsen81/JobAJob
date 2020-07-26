package jobajob.feature.dashboard.presentation.vacancydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.R
import jobajob.library.uicomponents.presentation.BaseNavigationFragment
import jobajob.library.uicomponents.util.withArgs
import kotlinx.android.synthetic.main.dashboard_fragment_vacancy_detail.*

@AndroidEntryPoint
internal class VacancyDetailFragment: BaseNavigationFragment() {

    private val viewModel: VacancyDetailViewModel by viewModels()

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
    ): View? = inflater.inflate(R.layout.dashboard_fragment_vacancy_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbVacTitle.text = "${dbVacTitle.text}  $vacancyId"

        hideRootNavigationView()

        btnAuthorize.setOnClickListener {
            requestUserAuthorization()
        }
    }
}