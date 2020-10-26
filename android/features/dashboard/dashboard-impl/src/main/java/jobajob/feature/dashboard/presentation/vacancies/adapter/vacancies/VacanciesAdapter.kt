package jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import jobajob.feature.dashboard.R
import jobajob.library.uicomponents.util.ChangePayload

internal class VacanciesAdapter(
    private val itemClickListener: ((clickedItemId: String) -> Unit)
) : ListAdapter<VacancyItem, VacancyViewHolder>(VacancyDiffUtilCallback()) {

    companion object {
        private val emptyChangePayload = ChangePayload(null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dashboard_item_vacancy, parent, false)
        return VacancyViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        getItem(position)?.also { holder.onBind(it, emptyChangePayload) }
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int, payloads: MutableList<Any>) {
        getItem(position)?.also { holder.onBind(it, ChangePayload(payloads)) }
    }
}