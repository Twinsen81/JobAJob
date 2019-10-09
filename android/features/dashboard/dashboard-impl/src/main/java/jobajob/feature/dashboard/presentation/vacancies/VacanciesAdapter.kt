package jobajob.feature.dashboard.presentation.vacancies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import jobajob.feature.dashboard.R
import jobajob.library.entity.vacancy.Vacancy
import jobajob.library.uicomponents.util.ChangePayload

internal class VacanciesAdapter(
    private val itemClickListener: ((clickedItem: Vacancy) -> Unit)? = null
) : ListAdapter<Vacancy, VacancyViewHolder>(VacanciesDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val viewHolder = VacancyViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.dashboard_item_vacancy, parent, false),
            itemClickListener
        )
        viewHolder.onCreated()
        return viewHolder
    }

    fun setData(data: List<Vacancy>) = submitList(data.map { it.copy() })

    override fun getItemId(position: Int) = getItem(position).id

    override fun getItemViewType(position: Int): Int = R.layout.dashboard_item_vacancy

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) =
        holder.onBind(getItem(position), ChangePayload(null))

    override fun onBindViewHolder(
        holder: VacancyViewHolder, position: Int, payloads: MutableList<Any>
    ) =
        holder.onBind(getItem(position), ChangePayload(payloads))
}