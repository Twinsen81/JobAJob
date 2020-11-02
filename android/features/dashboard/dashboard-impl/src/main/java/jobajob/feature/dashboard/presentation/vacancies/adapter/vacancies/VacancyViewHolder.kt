package jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jobajob.library.uicomponents.util.ChangePayload
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dashboard_item_vacancy.*

internal class VacancyViewHolder(
    override val containerView: View,
    private val itemClickListener: ((clickedItemId: String) -> Unit)
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var itemId: String = ""

    init {
        containerView.setOnClickListener {
            if (itemId.isNotEmpty()) itemClickListener.invoke(itemId)
        }
    }

    fun onBind(item: VacancyItem, updated: ChangePayload) {
        itemId = item.id

        if (updated(VacancyDiffUtilCallback.TITLES)) {

            vacancyTitle.text = item.title
            vacancyEmployerName.text = item.employerName
            vacancyCity.text = item.city
            if (item.salary != null) {
                vacancySalary.text = item.salary
                vacancySalary.visibility = View.VISIBLE
                vacancyCitySeparator.visibility = View.VISIBLE
            } else {
                vacancySalary.text = ""
                vacancySalary.visibility = View.GONE
                vacancyCitySeparator.visibility = View.GONE
            }
        }

        if (updated(VacancyDiffUtilCallback.FAVORITE)) {

        }
    }
}