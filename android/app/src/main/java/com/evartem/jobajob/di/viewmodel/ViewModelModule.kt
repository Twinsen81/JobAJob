package com.evartem.jobajob.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evartem.jobajob.LoginViewModel
import com.evartem.jobajob.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jobajob.library.uicomponents.di.ViewModelKey

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindViewModelMain(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindViewModelLogin(viewModel: LoginViewModel): ViewModel
}