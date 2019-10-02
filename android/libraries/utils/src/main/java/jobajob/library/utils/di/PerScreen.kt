package jobajob.library.utils.di

import javax.inject.Scope

/**
 * A Dagger's scope for an object that lives when a screen is active.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerScreen