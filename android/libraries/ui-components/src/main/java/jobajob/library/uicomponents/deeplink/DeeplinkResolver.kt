package jobajob.library.uicomponents.deeplink

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Creates the fragment after the right resolver was found for a deeplink.
 * Implementations of the interface are provided by features along with the key that is used
 * to find the right resolver.
 */
interface DeeplinkResolver {

    /**
     * Create the fragment that corresponds to this resolver
     */
    fun getDestination(params: Bundle): Fragment
}