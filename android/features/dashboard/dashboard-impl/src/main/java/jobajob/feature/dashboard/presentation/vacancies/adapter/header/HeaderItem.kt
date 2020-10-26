package jobajob.feature.dashboard.presentation.vacancies.adapter.header

import jobajob.feature.dashboard.presentation.vacancies.adapter.base.ItemWithId

internal class HeaderItem(val text: String) : ItemWithId() {
    override val id: String
        get() = "##HeaderItem## $text"
}