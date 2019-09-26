package com.evartem.jobajob

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(): ViewModel() {

    fun getButtonLabel() = "Login here"
}