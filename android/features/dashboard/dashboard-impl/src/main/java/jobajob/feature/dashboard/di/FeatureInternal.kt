package jobajob.feature.dashboard.di

import javax.inject.Qualifier

/**
 * Makes sure the object is provided from the feature's Dagger module.
 * If multiple features provide an object of the same type (say Retrofit) then
 * we need to tell one from another for injection (since all @Provides functions end up in the single
 * [dagger.hilt.android.components.ApplicationComponent]). When the object is marked with
 * this qualifier, then it is guaranteed that it is provided from within our feature.
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
internal annotation class FeatureInternal