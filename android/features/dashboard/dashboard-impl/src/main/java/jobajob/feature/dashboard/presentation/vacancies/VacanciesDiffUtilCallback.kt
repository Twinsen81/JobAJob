package jobajob.feature.dashboard.presentation.vacancies

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import jobajob.feature.vacancies.entity.Vacancy

internal class VacanciesDiffUtilCallback : DiffUtil.ItemCallback<Vacancy>() {

    companion object {
        const val TITLES = "CHANGED_TITLES"
        const val DETAILS = "CHANGED_DETAILS"
    }

    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Vacancy, newItem: Vacancy): Any? {
        val diffBundle = Bundle()

        with(newItem) {

            /* if (title != oldItem.title || employer != oldItem.employer
             ) diffBundle.putBoolean(TITLES, false)

             if (details != oldItem.details)
                 diffBundle.putBoolean(DETAILS, false)*/
        }

        return diffBundle
    }
}
