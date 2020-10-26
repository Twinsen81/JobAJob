package jobajob.feature.dashboard.presentation.vacancies.adapter.header

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dashboard_item_header.*

internal class HeaderViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(item: HeaderItem) {
        headerText.text = item.text
    }
}