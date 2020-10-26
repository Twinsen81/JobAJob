/*
package jobajob.feature.dashboard.presentation.vacancies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import jobajob.feature.dashboard.R
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.library.uicomponents.util.ChangePayload

internal class VacanciesAdapter(
    private val itemClickListener: ((clickedItem: Vacancy) -> Unit)? = null
) : PagedListAdapter<Vacancy, VacancyViewHolder>(VacanciesDiffUtilCallback()) {

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

    //override fun getItemId(position: Int) = getItem(position)?.id ?: RecyclerView.NO_ID

    override fun getItemViewType(position: Int): Int = R.layout.dashboard_item_vacancy

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val item = getItem(position)
        item?.also { holder.onBind(it, ChangePayload(null)) }
    }

    override fun onBindViewHolder(
        holder: VacancyViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        val item = getItem(position)
        item?.also { holder.onBind(item, ChangePayload(payloads)) }
    }
}*/
