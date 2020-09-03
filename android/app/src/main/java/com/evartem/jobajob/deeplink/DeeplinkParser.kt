package com.evartem.jobajob.deeplink

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import jobajob.library.uicomponents.deeplink.DeeplinkResolver
import timber.log.Timber
import javax.inject.Inject

/**
 * Parses a deeplink to find the tab to open and, optionally, the fragment to show in the opened tab.
 * Supported deeplinks formats:
 * jobajob://evartem.com/tabName
 * jobajob://evartem.com/tabName/fragmentName
 * or
 * http://evartem.com/tabName
 * http://evartem.com/tabName/fragmentName
 */
class DeeplinkParser @Inject constructor(
    private val deeplinkResolvers: Map<String, @JvmSuppressWildcards DeeplinkResolver>
) {
    fun parse(uri: Uri): DeepLinkDestination? {
        return uri.pathSegments.firstOrNull()?.let { tabName ->
            DeepLinkDestination(
                tabName = tabName,
                fragment = resolveDestination(uri),
                deeplink = uri.toString()
            )
        }
    }

    private fun resolveDestination(uri: Uri): Fragment? {
        return uri.lastPathSegment?.let { destinationName ->
            val destinationResolver = deeplinkResolvers[destinationName]
            return if (destinationResolver != null) {
                destinationResolver.getDestination(getQueryParameters(uri))
            } else {
                Timber.e("Failed to resolve destination \"$destinationName\" for the deeplink: $uri")
                null
            }
        }
    }

    private fun getQueryParameters(uri: Uri): Bundle {
        return Bundle().apply {
            uri.queryParameterNames.forEach { parameterName ->
                putString(parameterName, uri.getQueryParameter(parameterName))
            }
        }
    }
}