package jobajob.feature.dashboard.presentation.vacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import jobajob.feature.dashboard.R
import jobajob.feature.dashboard.data.remote.api.DashboardApi
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.library.uicomponents.presentation.BaseNavigationFragment
import kotlinx.android.synthetic.main.dashboard_fragment_vacancies.*
import javax.inject.Inject

internal class VacanciesFragment : BaseNavigationFragment() {

    private lateinit var viewModel: VacanciesViewModel
    private lateinit var recyclerViewAdapter: VacanciesAdapter
    private lateinit var recyclerViewLayoutManager: LinearLayoutManager

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

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        recyclerViewLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        vacanciesRecyclerView.layoutManager = recyclerViewLayoutManager

        recyclerViewAdapter = VacanciesAdapter { clickedMessage ->
            Snackbar.make(view!!, clickedMessage.id.toString(), Snackbar.LENGTH_SHORT).show()
            /* featureRouter.navigateTo(object : SupportAppScreen() {
                 override fun getFragment(): Fragment = VacancyDetailFragment.newInstance(6)
                       })
                   */
        }

        vacanciesRecyclerView.adapter = recyclerViewAdapter
        vacanciesRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onStart() {
        super.onStart()

        viewModel.vacancies.observe(viewLifecycleOwner, Observer { recyclerViewAdapter.submitList(it) })
    }
}