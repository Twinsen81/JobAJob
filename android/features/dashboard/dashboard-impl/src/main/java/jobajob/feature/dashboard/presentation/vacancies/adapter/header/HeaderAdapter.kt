package jobajob.feature.dashboard.presentation.vacancies.adapter.header

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import jobajob.feature.dashboard.R
import jobajob.feature.dashboard.presentation.vacancies.adapter.base.ItemWithId
import jobajob.feature.dashboard.presentation.vacancies.adapter.base.ItemWithIdDiffUtilCallback

internal class HeaderAdapter : ListAdapter<ItemWithId, HeaderViewHolder>(ItemWithIdDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dashboard_item_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        (getItem(position) as? HeaderItem)?.also { holder.onBind(it) }

    }
}