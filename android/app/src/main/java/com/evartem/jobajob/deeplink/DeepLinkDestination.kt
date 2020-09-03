package com.evartem.jobajob.deeplink

import androidx.fragment.app.Fragment

/**
 * Corresponds to the destination specified in the provided deeplink
 */
data class DeepLinkDestination(
    /**
     * The name of the tab to display the [fragment] in
     */
    val tabName: String,

    /**
     * The fragment to display (if null then just switch to the [tabName] without replacing the fragment)
     */
    val fragment: Fragment?,

    /**
     * String representation of the deep link for this destination
     */
    val deeplink: String
)