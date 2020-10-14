package jobajob.feature.dashboard.presentation.deeplink

import android.os.Bundle
import androidx.fragment.app.Fragment
import jobajob.feature.dashboard.presentation.vacancies.VacanciesFragment
import jobajob.feature.dashboard.presentation.vacancydetail.VacancyDetailFragment
import jobajob.library.uicomponents.deeplink.DeeplinkResolver

class VacanciesDeeplinkResolver : DeeplinkResolver {
    companion object {
        const val VACANCY_ID_PARAM = "id"
    }

    override fun getDestination(params: Bundle): Fragment {
        val vacancyId = params.getString(VACANCY_ID_PARAM)
        return if (vacancyId != null) {
            VacancyDetailFragment.newInstance(vacancyId)
        } else {
            VacanciesFragment()
        }
    }
}