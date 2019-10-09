package jobajob.library.uicomponents.util

import android.os.Bundle

/**
 * A wrapper class for the change payload of DiffUtil's getChangePayload.
 * Provides a convenient function to check what changed in the item's data.
 */
class ChangePayload(payloads: MutableList<Any>?) {

    private val gotPartialChanges: Boolean = payloads?.isNotEmpty() ?: false
    private val changePayload: Bundle?

    init {
        changePayload = if (gotPartialChanges) payloads!![0] as Bundle else null
    }

    /**
     * Tells if the property's view should be updated because the corresponding
     * field of the data object was changed or the whole data object changed (no payload).
     */
    operator fun invoke(property: String) =
        !gotPartialChanges || changePayload!!.containsKey(property)
}