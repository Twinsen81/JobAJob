package com.evartem.jobajob.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val providers: Map<
        Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider = providers[modelClass] ?:
        providers.asIterable().firstOrNull {modelClass.isAssignableFrom(it.key)}
            ?.value
        ?: throw IllegalArgumentException("Unknown model class: $modelClass")

        @Suppress("UNCHECKED_CAST")
        try {
            return provider.get() as T
        }catch(ex: Exception) {
            throw RuntimeException(ex)
        }
    }
}