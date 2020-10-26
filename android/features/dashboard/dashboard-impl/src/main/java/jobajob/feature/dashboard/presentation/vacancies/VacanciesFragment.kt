package jobajob.feature.dashboard.presentation.vacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.R
import jobajob.feature.dashboard.presentation.vacancies.adapter.header.HeaderAdapter
import jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies.VacanciesAdapter
import jobajob.feature.dashboard.presentation.vacancydetail.VacancyDetailFragment
import jobajob.library.navigation.api.ScreenNavigator
import kotlinx.android.synthetic.main.dashboard_fragment_vacancies.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
internal class VacanciesFragment : Fragment() {

    @ExperimentalCoroutinesApi
    private val viewModel: VacanciesViewModel by viewModels()

    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var vacanciesAdapter: VacanciesAdapter

    private lateinit var recyclerViewLayoutManager: LinearLayoutManager

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state
                .onEach { renderState(it) }
                .catch { Timber.e(it, "Error processing a state from ViewModel") }
                .collect()

            viewModel.event
                .onEach { processEvent(it) }
                .catch { Timber.e(it, "Error processing an event from ViewModel") }
                .collect()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dashboard_fragment_vacancies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        recyclerViewLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        vacanciesRecyclerView.layoutManager = recyclerViewLayoutManager

        headerAdapter = HeaderAdapter()
        vacanciesAdapter = VacanciesAdapter { clickedVacancyId ->
//            AnalyticsViewVacancyEvent(clickedVacancyId)
//            screenNavigator.navigateTo(VacancyDetailFragment.newInstance(clickedVacancyId))
        }
        concatAdapter = ConcatAdapter(headerAdapter, vacanciesAdapter)

        vacanciesRecyclerView.adapter = concatAdapter
        vacanciesRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun renderState(state: VacanciesViewState) {
        headerAdapter.submitList(listOf(state.header))
        vacanciesAdapter.submitList(state.vacancies)
    }

    private fun processEvent(event: VacanciesViewModelEvent) {
        return when (event) {
            is VacanciesViewModelEvent.DisplayError -> showSnack(event.text)
            is VacanciesViewModelEvent.DisplayVacancy ->
                screenNavigator.navigateTo(VacancyDetailFragment.newInstance(event.vacancyId))
        }
    }

    private fun showSnack(text: String) {
        view?.also { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }
    }
}