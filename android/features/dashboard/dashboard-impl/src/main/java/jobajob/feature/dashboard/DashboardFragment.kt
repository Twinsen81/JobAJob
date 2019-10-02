package jobajob.feature.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.dashboard.di.DaggerDashboardFeatureComponent
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject
import kotlin.random.Random

internal class DashboardFragment: Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DashboardViewModel

    private var rnd = Random.nextInt()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DashboardFeatureComponent.get().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        dbTitle.text = "${dbTitle.text}  $rnd"

        tag
    }


}