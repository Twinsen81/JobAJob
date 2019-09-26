package com.evartem.jobajob

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(): ViewModel() {
    fun getStartLabel() = "Welcome! Please, login."
}