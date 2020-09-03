package com.evartem.jobajob.presentation

import androidx.fragment.app.Fragment
import javax.inject.Provider

/**
 * A tab in the bottom navigation view
 */
data class Tab(
    /**
     * Ordinal number (left-to-right)
     */
    val index: Int,

    /**
     * The name to reference in deeplinks
     */
    val name: String,

    /**
     * The corresponding menu id of the bottom navigation bar
     */
    val menuId: Int,

    /**
     * The fragment to display in the tab when it is opened for the first time
     */
    val fragment: Provider<out Fragment>
)

fun List<Tab>.fragmentByIndex(tabIndex: Int): Fragment {
    return if (tabIndex < size) {
        first { it.index == tabIndex }.fragment.get()
    } else {
        throw IllegalArgumentException("Unknown root fragment with index $tabIndex")
    }
}

fun List<Tab>.menuIdByIndex(tabIndex: Int): Int {
    return if (tabIndex < size) {
        first { it.index == tabIndex }.menuId
    } else {
        throw IllegalArgumentException("Unknown root fragment with index $tabIndex")
    }
}

fun List<Tab>.indexByMenuId(menuId: Int): Int = first { it.menuId == menuId }.index

fun List<Tab>.indexByName(name: String): Int? = firstOrNull { it.name == name }?.index

fun List<Tab>.nameByMenuId(menuId: Int): String = first { it.menuId == menuId }.name
