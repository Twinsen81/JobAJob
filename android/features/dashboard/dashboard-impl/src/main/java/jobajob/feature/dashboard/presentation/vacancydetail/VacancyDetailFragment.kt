package jobajob.feature.dashboard.presentation.vacancydetail

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import androidx.core.content.res.use
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.R
import jobajob.feature.vacancies.entity.SalaryType
import jobajob.library.navigation.api.ScreenNavigator
import jobajob.library.uicomponents.util.withArgs
import kotlinx.android.synthetic.main.dashboard_fragment_vacancy_detail.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
internal class VacancyDetailFragment : Fragment(R.layout.dashboard_fragment_vacancy_detail) {

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    private val viewModel: VacancyDetailViewModel by viewModels()

    private var vacancyIsFavoriteColor: Int = 0
    private var vacancyIsNotFavoriteColor: Int = 0

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

        initToolbar()
    }

    @SuppressLint("RestrictedApi", "ResourceType")
    private fun initToolbar() {
        vacancyDetailToolbar.setNavigationOnClickListener {
            screenNavigator.goBack()
        }

        vacancyDetailToolbar.context
            .obtainStyledAttributes(intArrayOf(R.attr.colorOnPrimary, R.attr.colorOnSecondary)).use {
                vacancyIsFavoriteColor = it.getColor(0, 0)
                vacancyIsNotFavoriteColor = it.getColor(1, 0)
            }

        val item = vacancyDetailToolbar.menu.findItem(R.id.vacancyDetailFavorite)
        item.setOnMenuItemClickListener {
            it.isChecked = !it.isChecked
            renderFavoriteState(it.isChecked)
            true
        }
    }

    private fun renderState(state: VacancyDetailViewState) {
        when (state) {
            is VacancyDetailViewState.Loading -> {
                vacancyDetailLoadingView.visibility = View.VISIBLE
            }
            is VacancyDetailViewState.Data -> {
                vacancyDetailLoadingView.visibility = View.GONE
                renderVacancy(state)
            }
            is VacancyDetailViewState.Error -> {
            }
        }
    }

    private fun renderVacancy(data: VacancyDetailViewState.Data) {
        with(data) {
            vacancyDetailCity.text = vacancy.city
            vacancyDetailTitle.text = vacancy.title
            vacancyDetailSalary.text = getVacancySalary(vacancy.salaryType, vacancy.salaryMin, vacancy.salaryMax)
            vacancyDetailExperience.text = resources.getString(R.string.vacancy_detail_experience, vacancy.experience)
            vacancyDetailSchedule.text = resources.getString(R.string.vacancy_detail_schedule, vacancy.schedule)
            vacancyDetailEmployerLogo.load(vacancy.employer.logoUrl) {
                placeholder(R.drawable.vacancy_detail_logo_placeholder)
                transformations(CircleCropTransformation())
            }
            vacancyDetailEmployerName.text = vacancy.employer.name
            vacancyDetailDescription.text = vacancy.description
            renderFavoriteState(isFavorite)
            renderRequiredSkills(vacancy.skills)
        }
    }

    private fun renderRequiredSkills(skills: List<String>) {
        if (skills.isEmpty()) {
            vacancyDetailSkillsContainer.visibility = View.GONE
            return
        } else {
            vacancyDetailSkills.removeAllViews()
            vacancyDetailSkillsContainer.visibility = View.VISIBLE
            skills.forEach { skill ->
                val chip = Chip(context)
                chip.maxLines = 1
                chip.ellipsize = TextUtils.TruncateAt.END
                chip.chipMinHeight = dp2pixels(32)
                chip.text = skill
                chip.chipStartPadding = dp2pixels(2)
                chip.chipEndPadding = dp2pixels(2)
                vacancyDetailSkills.addView(chip)
                val lp = chip.layoutParams as FlexboxLayout.LayoutParams
                lp.rightMargin = dp2pixels(8).roundToInt()
                chip.layoutParams = lp
            }
        }
    }

    private fun getVacancySalary(salaryType: SalaryType?, salaryMin: Int?, salaryMax: Int?): String {
        return when {
            (salaryType == null || (salaryMin == null && salaryMax == null)) ->
                resources.getString(R.string.vacancy_detail_salary_no)
            salaryMin == null -> resources.getString(R.string.vacancy_detail_salary_up_to, salaryMax.toString())
            salaryMax == null -> resources.getString(R.string.vacancy_detail_salary_from, salaryMin.toString())
            else -> resources.getString(R.string.vacancy_detail_salary_up_to, salaryMax.toString())
        }
    }

    private fun renderFavoriteState(isFavorite: Boolean) {
        val item = vacancyDetailToolbar.menu.findItem(R.id.vacancyDetailFavorite)
        MenuItemCompat.setIconTintList(
            item, ColorStateList.valueOf(if (isFavorite) vacancyIsFavoriteColor else vacancyIsNotFavoriteColor)
        )
    }

    fun dp2pixels(dp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            requireContext().resources.displayMetrics
        )
    }

}