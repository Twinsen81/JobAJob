/*
package jobajob.feature.dashboard.presentation.vacancies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jobajob.feature.dashboard.presentation.vacancies.VacanciesDiffUtilCallback.Companion.DETAILS
import jobajob.feature.dashboard.presentation.vacancies.VacanciesDiffUtilCallback.Companion.TITLES
import jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies.VacancyDiffUtilCallback.Companion.FAVORITE
import jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies.VacancyItem
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.library.uicomponents.util.ChangePayload
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dashboard_item_vacancy.*

internal class VacancyViewHolder(
    override val containerView: View,
    private val itemClickListener: ((clickedItem: Vacancy) -> Unit)? = null
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var vacancy: VacancyItem

    */
/**
 * Called only once from the adapter's onCreateViewHolder
 *//*

    fun onCreated() {
        if (itemClickListener != null)
            containerView.setOnClickListener {
                itemClickListener.invoke(vacancy.id)
            }
    }

    */
/**
 * Called multiple times from the adapter's onBindViewHolder
 *//*

    fun onBind(vacancy: VacancyItem, updated: ChangePayload) {

        this.vacancy = vacancy

        if (updated(TITLES)) {

            vacancyTitle.text = vacancy.title
            vacancyEmployerName.text = vacancy.employer.name
        }

        if (updated(FAVORITE)) {}
    }
}*/
