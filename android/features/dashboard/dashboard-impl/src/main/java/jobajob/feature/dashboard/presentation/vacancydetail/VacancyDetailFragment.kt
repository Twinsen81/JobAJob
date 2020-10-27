package jobajob.feature.dashboard.presentation.vacancydetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.R
import jobajob.library.navigation.api.ScreenNavigator
import jobajob.library.uicomponents.util.withArgs
import kotlinx.android.synthetic.main.dashboard_fragment_vacancy_detail.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
internal class VacancyDetailFragment : Fragment(R.layout.dashboard_fragment_vacancy_detail) {

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    private val viewModel: VacancyDetailViewModel by viewModels()

    companion object {
        private const val VACANCY_ID_KEY = "vacancy_id"

        fun newInstance(vacancyId: String) =
            VacancyDetailFragment().withArgs {
                putString(VACANCY_ID_KEY, vacancyId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = requireNotNull(arguments)
        val vacancyId = requireNotNull(args.getString(VACANCY_ID_KEY))

        viewModel.init(vacancyId)

        lifecycleScope.launchWhenStarted {
            viewModel.state
                .onEach { renderState(it) }
                .catch { Timber.e(it, "Error processing a state from ViewModel") }
                .collect()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenNavigator.hideNavigation()

        vacancyDetailToolbar.backNavigationListener = { screenNavigator.goBack() }
    }

    private fun renderState(state: VacancyDetailViewState) {
        vacancyDetailTitle.text = when (state) {
            is VacancyDetailViewState.Loading -> "Loading..."
            is VacancyDetailViewState.Data -> state.vacancy.title
            is VacancyDetailViewState.Error -> "Oops... Error"
        }
    }
}