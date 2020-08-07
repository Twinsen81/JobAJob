package com.evartem.jobajob.presentation

import androidx.fragment.app.Fragment
import javax.inject.Provider

class Tabs(private val tabs: List<Pair<Int, Provider<out Fragment>>>) {

    fun getFragmentByIndex(tabIndex: Int): Fragment {
        return if (tabIndex < tabs.size) {
            tabs[tabIndex].second.get()
        } else {
            throw IllegalArgumentException("Unknown root fragment with index $tabIndex")
        }
    }

    fun getNavMenuIdByIndex(tabIndex: Int): Int {
        return if (tabIndex < tabs.size) {
            tabs[tabIndex].first
        } else {
            throw IllegalArgumentException("Unknown root fragment with index $tabIndex")
        }
    }

    fun getTabIndexByNavMenuId(menuId: Int): Int = tabs.indexOfFirst { it.first == menuId }
}