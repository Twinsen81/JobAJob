package jobajob.feature.dashboard.presentation.vacancies.adapter.base

import androidx.recyclerview.widget.DiffUtil

/**
 * Simple DiffUtil for simple items
 */
internal class ItemWithIdDiffUtilCallback : DiffUtil.ItemCallback<ItemWithId>() {
    override fun areItemsTheSame(oldItem: ItemWithId, newItem: ItemWithId): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemWithId, newItem: ItemWithId) = true

    override fun getChangePayload(oldItem: ItemWithId, newItem: ItemWithId): Any? = Any()
}