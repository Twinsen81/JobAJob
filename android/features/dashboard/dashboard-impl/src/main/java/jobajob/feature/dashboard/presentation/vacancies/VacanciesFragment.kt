package jobajob.feature.dashboard.presentation.vacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.R
import jobajob.feature.dashboard.data.remote.api.DashboardServerApi
import jobajob.feature.dashboard.presentation.vacancydetail.VacancyDetailFragment
import jobajob.library.navigation.api.ScreenNavigator
import kotlinx.android.synthetic.main.dashboard_fragment_vacancies.*
import javax.inject.Inject

@AndroidEntryPoint
internal class VacanciesFragment : Fragment() {

    private val viewModel: VacanciesViewModel by viewModels()
    private lateinit var recyclerViewAdapter: VacanciesAdapter
    private lateinit var recyclerViewLayoutManager: LinearLayoutManager

    @Inject
    lateinit var serverApi: DashboardServerApi

    @Inject
    lateinit var screenNavigator: ScreenNavigator

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

        recyclerViewAdapter = VacanciesAdapter { clickedVacancy ->
            screenNavigator.navigateTo(VacancyDetailFragment.newInstance(clickedVacancy.id))
        }

        vacanciesRecyclerView.adapter = recyclerViewAdapter
        vacanciesRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onStart() {
        super.onStart()

        viewModel.vacancies.observe(viewLifecycleOwner, Observer { recyclerViewAdapter.submitList(it) })
    }
}