package com.evartem.jobajob

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jobajob.library.session.Session
import jobajob.library.session.SessionManager
import jobajob.library.session.SessionManagerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

/**
 * Here we provide app-lifecycle dependencies that are not provided in separate wiring-modules
 */
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AppWiring {

    @[Provides Singleton]
    fun provideSessionManager(): SessionManager = SessionManagerImpl()

    @Provides
    fun provideSession(sessionManager: SessionManager): Session = sessionManager
}