package jobajob.feature.dashboard.presentation.vacancies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jobajob.feature.dashboard.presentation.vacancies.VacanciesDiffUtilCallback.Companion.DETAILS
import jobajob.feature.dashboard.presentation.vacancies.VacanciesDiffUtilCallback.Companion.TITLES
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.library.uicomponents.util.ChangePayload
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dashboard_item_vacancy.*

internal class VacancyViewHolder(
    override val containerView: View,
    private val itemClickListener: ((clickedItem: Vacancy) -> Unit)? = null
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var vacancy: Vacancy

    /**
     * Called only once from the adapter's onCreateViewHolder
     */
    fun onCreated() {
        if (itemClickListener != null)
            containerView.setOnClickListener {
                itemClickListener.invoke(vacancy)
            }
    }

    /**
     * Called multiple times from the adapter's onBindViewHolder
     */
    fun onBind(vacancy: Vacancy, updated: ChangePayload) {

        this.vacancy = vacancy

        if (updated(TITLES)) {
            tvVacancyTitle.text = vacancy.title
            tvVacancyCompany.text = vacancy.employer.name
            tvVacancySalary.text = vacancy.industry.industry
        }

        if (updated(DETAILS))
            tvVacancyDetails.text = vacancy.title
    }
}