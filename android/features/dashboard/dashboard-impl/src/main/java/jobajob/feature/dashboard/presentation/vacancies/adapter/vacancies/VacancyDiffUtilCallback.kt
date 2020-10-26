package jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

internal class VacancyDiffUtilCallback : DiffUtil.ItemCallback<VacancyItem>() {

    companion object {
        const val TITLES = "CHANGED_TITLES"
        const val FAVORITE = "CHANGED_FAVORITE"
    }

    override fun areItemsTheSame(oldItem: VacancyItem, newItem: VacancyItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VacancyItem, newItem: VacancyItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: VacancyItem, newItem: VacancyItem): Any? {
        val diffBundle = Bundle()

        with(newItem) {

            if (title != oldItem.title ||
                city != oldItem.city ||
                employerName != oldItem.employerName ||
                salary != oldItem.salary
            )
                diffBundle.putBoolean(TITLES, false)

            if (isFavorite != oldItem.isFavorite) diffBundle.putBoolean(FAVORITE, false)
        }

        return diffBundle
    }
}